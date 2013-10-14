package com.enioka.jqm.tools;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.enioka.jqm.jpamodel.Deliverable;
import com.enioka.jqm.jpamodel.DeploymentParameter;
import com.enioka.jqm.jpamodel.ExecParameter;
import com.enioka.jqm.jpamodel.History;
import com.enioka.jqm.jpamodel.JobDefParameter;
import com.enioka.jqm.jpamodel.JobDefinition;
import com.enioka.jqm.jpamodel.JobInstance;
import com.enioka.jqm.jpamodel.JobParameter;
import com.enioka.jqm.jpamodel.Message;
import com.enioka.jqm.jpamodel.Node;
import com.enioka.jqm.jpamodel.Queue;

public class CreationTools
{
	public static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("jobqueue-api-pu");
	public static final EntityManager em = emf.createEntityManager();

	public CreationTools()
	{
	}

	// ------------------ DELIVERABLES ------------------------

	public static Deliverable createDeliverable(String fp, String hp, String ff, Integer jobId)
	{
		Deliverable j = new Deliverable();
		EntityTransaction transac = em.getTransaction();
		transac.begin();

		j.setFilePath(fp);
		j.setHashPath(hp);
		j.setFileFamily(ff);
		j.setJobId(jobId);

		em.persist(j);
		transac.commit();

		return j;
	}

	// ------------------ JOBDEFINITION ------------------------

	public static JobDefinition initJobDefinition(String javaClassName, String filePath, Queue queue)
	{
		JobDefinition j = new JobDefinition();
		EntityTransaction transac = em.getTransaction();
		transac.begin();

		j.setJavaClassName(javaClassName);
		j.setFilePath(filePath);
		j.setQueue(queue);

		em.persist(j);
		transac.commit();

		return j;
	}

	public static JobDefinition createJobDefinition(boolean canBeRestarted, String javaClassName, List<JobDefParameter> jps, String filePath, String jp,
			 						Queue queue, Integer maxTimeRunning, String applicationName, Integer sessionID,
			 						String application, String module, String other1, String other2, String other3,
			 						boolean highlander)
	{
		JobDefinition j = new JobDefinition();
		EntityTransaction transac = em.getTransaction();
		transac.begin();

		// ------------------

		j.setCanBeRestarted(canBeRestarted);
		j.setJavaClassName(javaClassName);
		j.setParameters(jps);
		j.setFilePath(filePath);
		j.setQueue(queue);
		j.setMaxTimeRunning(maxTimeRunning);
		j.setApplicationName(applicationName);
		j.setSessionID(sessionID);
		j.setApplication(application);
		j.setModule(module);
		j.setOther1(other1);
		j.setOther2(other2);
		j.setOther3(other3);
		j.setHighlander(highlander);
		j.setJarPath(jp);

		em.persist(j);
		transac.commit();

		return j;
	}

	// ------------------ DEPLOYMENTPARAMETER ------------------

	public static DeploymentParameter initDeploymentParameter(Node node, Integer nbThread)
	{
		DeploymentParameter dp = new DeploymentParameter();
		EntityTransaction transac = em.getTransaction();
		transac.begin();

		dp.setNode(node);
		dp.setNbThread(nbThread);

		em.persist(dp);
		transac.commit();
		return dp;
	}

	public static DeploymentParameter createDeploymentParameter(Integer classId, Node node, Integer nbThread, Integer pollingInterval, Queue qVip)
	{
		DeploymentParameter dp = new DeploymentParameter();
		EntityTransaction transac = em.getTransaction();
		transac.begin();

		dp.setClassId(classId);
		dp.setNode(node);
		dp.setNbThread(nbThread);
		dp.setPollingInterval(pollingInterval);
		dp.setQueue(qVip);

		em.persist(dp);
		transac.commit();
		return dp;
	}

	// ------------------ EXECPARAMETER ------------------------

	public static ExecParameter createExecParameter(String key, String value, JobInstance jobInstance)
	{
		ExecParameter e = new ExecParameter();
		EntityTransaction transac = em.getTransaction();
		transac.begin();

		e.setKey(key);
		e.setValue(value);
		e.setJobInstance(jobInstance);

		em.persist(e);
		transac.commit();
		return e;
	}

	// ------------------ HISTORY ------------------------------

	public static History initHistory(Integer returnedValue, Message message, JobInstance jobInstance)
	{
		History h = new History();
		EntityTransaction transac = em.getTransaction();
		transac.begin();

		h.setReturnedValue(returnedValue);
		h.setMessage(message);
		h.setJobInstance(jobInstance);

		em.persist(h);
		transac.commit();
		return h;
	}

	public static History createhistory(Integer returnedValue, Calendar jobDate, String msg, Message message,
			JobInstance jobInstance,
			Calendar enqueueDate,
			Calendar executionDate,
			Calendar endDate)
	{
		History h = new History();
		EntityTransaction transac = em.getTransaction();
		transac.begin();

		h.setReturnedValue(returnedValue);
		h.setJobDate(jobDate);
		h.setMsg(msg);
		h.setMessage(message);
		h.setJobInstance(jobInstance);
		h.setEnqueueDate(enqueueDate);
		h.setExecutionDate(executionDate);
		h.setEndDate(endDate);

		em.persist(h);
		transac.commit();
		return h;
	}

	// ------------------ JOBINSTANCE --------------------------

	public static JobInstance createJobInstance(JobDefinition jd, List<JobParameter> jps, String user, Integer sessionID, String state, Integer position, Queue queue)
	{
		JobInstance j = new JobInstance();
		EntityTransaction transac = em.getTransaction();
		transac.begin();

		j.setJd(jd);
		j.setParameters(jps);
		j.setSessionID(sessionID);
		j.setUser(user);
		j.setState(state);
		j.setPosition(position);
		j.setQueue(queue);

		em.persist(j);
		transac.commit();

		return j;
	}

	// ------------------ JOBPARAMETER -------------------------

	public static JobParameter createJobParameter(String key, String value)
	{
		JobParameter j = new JobParameter();
		EntityTransaction transac = em.getTransaction();
		transac.begin();

		j.setKey(key);
		j.setValue(value);

		em.persist(j);
		transac.commit();
		return j;
	}

	public static JobDefParameter createJobDefParameter(String key, String value)
	{
		JobDefParameter j = new JobDefParameter();
		EntityTransaction transac = em.getTransaction();
		transac.begin();

		j.setKey(key);
		j.setValue(value);

		em.persist(j);
		transac.commit();
		return j;
	}

	// ------------------ MESSAGE ------------------------------

	public static Message createMessage(String textMessage, History history)
	{
		Message m = new Message();
		EntityTransaction transac = em.getTransaction();
		transac.begin();

		m.setTextMessage(textMessage);
		m.setHistory(history);

		em.persist(m);
		transac.commit();
		return m;
	}

	// ------------------ NODE ---------------------------------

	public static Node createNode(String listeningInterface, Integer port)
	{
		Node n = new Node();
		EntityTransaction transac = em.getTransaction();
		transac.begin();

		n.setListeningInterface(listeningInterface);
		n.setPort(port);

		em.persist(n);
		transac.commit();
		return n;
	}

	// ------------------ QUEUE --------------------------------

	public static Queue initQueue(String name, String description, Integer maxTempInQueue, Integer maxTempRunning)
	{
		Queue q = new Queue();
		EntityTransaction transac = em.getTransaction();
		transac.begin();

		q.setName(name);
		q.setDescription(description);
		q.setMaxTempInQueue(maxTempInQueue);
		q.setMaxTempRunning(maxTempRunning);

		em.persist(q);
		transac.commit();

		return q;
	}

	public static Queue createQueue(String name, String description, Integer maxTempInQueue, Integer maxTempRunning,
			boolean defaultQueue)
	{
		Queue q = new Queue();
		EntityTransaction transac = em.getTransaction();
		transac.begin();

		q.setName(name);
		q.setDescription(description);
		q.setMaxTempInQueue(maxTempInQueue);
		q.setMaxTempRunning(maxTempRunning);
		q.setDefaultQueue(defaultQueue);

		em.persist(q);
		transac.commit();
		return q;
	}

	// ------------------ CLOSE ENTITYs ------------------------

	public static void close()
	{
		em.close();
		emf.close();
	}
}