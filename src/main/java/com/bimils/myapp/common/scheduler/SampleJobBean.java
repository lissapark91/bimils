package com.bimils.myapp.common.scheduler;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class SampleJobBean extends QuartzJobBean{
	
	private SampleTask sampleTask;
	
	public void setSampleTask(SampleTask sampleTask) {
		this.sampleTask = sampleTask;
	}

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		// TODO Auto-generated method stub
		sampleTask.execute();
	}

}
