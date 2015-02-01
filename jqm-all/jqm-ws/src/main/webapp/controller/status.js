'use strict';

var jqmControllers = angular.module('jqmControllers');

jqmControllers.controller('µStatusCtrl', function($scope, $http, µQueueDto, µNodeDto, µQueueMappingDto)
{
    $scope.mappings = null;

    µQueueMappingDto.query(function(res)
    {
        $scope.mappings = res;

        var g = new dagreD3.dagre.graphlib.Graph({
            compound : true,
            directed : true,
        });
        g.setGraph({});
        g.setDefaultEdgeLabel(function()
        {
            return {};
        });

        var db_id = 'qSubGraph';
        g.setNode(db_id, {
            label : 'meuh',
            shape : 'circle',
        });
        g.setNode(db_id + '-title', {
            label : 'database',
        });
        g.setParent(db_id + '-title', db_id);

        for ( var i = 0; i < $scope.mappings.length; i++)
        {
            var n = $scope.mappings[i];

            // JQM node itself (with embedded title)
            var n_id = 'node-' + n.nodeId;
            g.setNode(n_id, {
                label : n.nodeName,
            });
            g.setNode(n_id + '-title', {
                label : 'node ' + n.nodeName,
            });
            g.setParent(n_id + '-title', n_id);

            // Queue inside node
            var qn_id = 'queue-' + n.queueId + "-" + n.nodeId;
            g.setNode(qn_id, {
                label : '0/' + n.nbThread,
                width : 50,
                height : 50,
                shape: 'rect',
            });
            g.setParent(qn_id, n_id);

            // Queue inside database
            var q_id = 'queue-' + n.queueId;
            g.setNode(q_id, {
                label : n.queueName,
                width : 100,
                height : 100,
                style : 'fill: blue;'
            });
            g.setParent(q_id, db_id);

            // Link from node to queue
            g.setEdge(qn_id, q_id, {
                label : '' + n.pollingInterval / 1000 + 's'
            });
        }

        dagreD3.dagre.layout(g);

        var renderer = dagreD3.render();
        $("svg g").empty();
        d3.select("svg g").call(renderer, g);
    });

    // g.setEdge("marsu1","marsu2");

});
