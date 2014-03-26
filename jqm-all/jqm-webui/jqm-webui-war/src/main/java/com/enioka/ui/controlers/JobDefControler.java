package com.enioka.ui.controlers;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.ListDataModel;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.SelectableDataModel;

import com.enioka.jqm.api.JobDef;
import com.enioka.jqm.api.JobRequest;
import com.enioka.jqm.api.JqmClientFactory;

@ManagedBean(eager = true)
@SessionScoped
public class JobDefControler extends ListDataModel<JobDef> implements Serializable, SelectableDataModel<JobDef>
{
    private static final long serialVersionUID = -608970776489109835L;

    private JobDef selected = null;
    private String userName = "user";

    public JobDefControler()
    {}

    public ListDataModel<JobDef> getJobs()
    {
        this.setWrappedData(JqmClientFactory.getClient().getJobDefinitions());
        return this;
    }

    public String enqueue()
    {
        JobRequest jr = new JobRequest(this.selected.getApplicationName(), this.userName);
        int i = JqmClientFactory.getClient().enqueue(jr);

        FacesMessage msg = new FacesMessage("Enqueue done", "request number " + i);
        FacesContext.getCurrentInstance().addMessage(null, msg);

        return "jobdef?faces-redirect=true";
    }

    public void setSelectedJob(JobDef job)
    {
        selected = job;
    }

    public JobDef getSelectedJob()
    {
        return selected;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public void onRowSelect(SelectEvent event)
    {
        FacesMessage msg = new FacesMessage("JD Selected", ((JobDef) event.getObject()).getApplicationName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowUnselect(UnselectEvent event)
    {
        FacesMessage msg = new FacesMessage("JD Unselected", ((JobDef) event.getObject()).getApplicationName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    @Override
    public Object getRowKey(JobDef object)
    {
        return object.getId();
    }

    @Override
    public JobDef getRowData(String rowKey)
    {
        for (JobDef jd : JqmClientFactory.getClient().getJobDefinitions())
        {
            if (jd.getId().toString().equals(rowKey))
            {
                return jd;
            }
        }
        return null;
    }
}
