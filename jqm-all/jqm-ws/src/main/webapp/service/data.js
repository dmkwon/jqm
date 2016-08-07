'use strict';

var jqmServices = angular.module('jqmServices',
		[ 'ngResource', 'jqmConstants' ]);

jqmServices.factory('µProfileDto', [ '$resource', function($resource) {
	return $resource('ws/admin/profile/:id', {
		id : ''
	}, {
		saveAll : {
			method : 'PUT',
			isArray : true
		},
	});
} ]);

jqmServices.factory('µNodeDto', [ '$resource', 'selectedProfile',
		function($resource, selectedProfile) {
			return $resource('ws/admin/profile/:profileId/node/:id', {
				profileId : function() {
					return selectedProfile.id;
				}
			}, {
				saveAll : {
					method : 'PUT',
					isArray : true
				},
			});
		} ]);

jqmServices.factory('µQueueDto', [ '$resource', 'selectedProfile',
		function($resource, selectedProfile) {
			return $resource('ws/admin/profile/:profileId/q/:id', {
				profileId : function() {
					return selectedProfile.id;
				},
				id : ''
			}, {
				query : {
					method : 'GET',
					isArray : true
				},
				saveAll : {
					method : 'PUT',
					isArray : true
				},

				/*
				 * remove : { method : 'DELETE' },
				 */
				save : {
					method : 'POST'
				},
			});
		} ]);

jqmServices.factory('µQueueMappingDto', [ '$resource', 'selectedProfile',
		function($resource, selectedProfile) {
			return $resource('ws/admin/profile/:profileId/qmapping/:id', {
				profileId : function() {
					return selectedProfile.id
				}
			}, {
				saveAll : {
					method : 'PUT',
					isArray : true
				},
			});
		} ]);

jqmServices.factory('µJndiDto', [ '$resource', 'selectedProfile',
		function($resource, selectedProfile) {
			return $resource('ws/admin/profile/:profileId/jndi/:id', {
				profileId : function() {
					return selectedProfile.id
				}
			});
		} ]);

jqmServices.factory('µPrmDto', function($resource) {
	return $resource('ws/admin/prm/:id', {
		id : ''
	}, {
		saveAll : {
			method : 'PUT',
			isArray : true
		},
	});
});

jqmServices.factory('µJdDto', [ '$resource', 'selectedProfile',
		function($resource, selectedProfile) {
			return $resource('ws/admin/profile/:profileId/jd/:id', {
				profileId : function() {
					return selectedProfile.id
				}
			}, {
				saveAll : {
					method : 'PUT',
					isArray : true
				},
			});
		} ]);

jqmServices.factory('µUserDto', function($resource) {
	return $resource('ws/admin/user/:id', {
		id : ''
	}, {
		saveAll : {
			method : 'PUT',
			isArray : true
		},
	});
});

jqmServices.factory('µRoleDto', function($resource) {
	return $resource('ws/admin/role/:id', {
		id : ''
	}, {
		saveAll : {
			method : 'PUT',
			isArray : true
		},
	});
});

jqmServices.factory('µUserPerms', function($resource) {
	return $resource('ws/admin/me', {}, {
		query : {
			method : 'GET',
			isArray : false
		},
	});
});

jqmServices.factory('µUserJdDto', function($resource) {
	return $resource('ws/client/jd/:id', {
		id : ''
	});
});