package com.config;

import com.quartz.JobImpl;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by dqf on 2015/8/14.
 */
@Configuration
@ConditionalOnProperty(name = "quartz.enabled")
public class SchedledConfig {
    @Bean
    public JobImpl quartzJob(){
        JobImpl jobimpl = new JobImpl();
        return jobimpl;
    }

    @Bean
    public MethodInvokingJobDetailFactoryBean jobtask(){
        MethodInvokingJobDetailFactoryBean mfb = new MethodInvokingJobDetailFactoryBean();
        mfb.setTargetObject(quartzJob());
        mfb.setTargetMethod("evalExpire");
        mfb.setConcurrent(false);
        return mfb;
    }

    @Bean
    public CronTriggerFactoryBean doTime(){
        CronTriggerFactoryBean cBean = new CronTriggerFactoryBean();
        cBean.setJobDetail(jobtask().getObject());
        cBean.setCronExpression("0 */1 * * * ?");
        return cBean;
    }

    @Bean(autowire = Autowire.NO)
    public SchedulerFactoryBean startQuertz(){
        SchedulerFactoryBean sBean = new SchedulerFactoryBean();
        List<Trigger> list = new ArrayList<Trigger>();
        sBean.setTriggers(doTime().getObject());
        Properties properties = new Properties();
        properties.put("org.quartz.threadPool.threadCount","5");
        sBean.setQuartzProperties(properties);
        sBean.setStartupDelay(2);

        return sBean;
    }


}




/**
 @Bean
 public JobFactory jobFactory(ApplicationContext applicationContext)
 {
 AutowiringSpringBeanJobFactory jobFactory = new AutowiringSpringBeanJobFactory();
 jobFactory.setApplicationContext(applicationContext);
 return jobFactory;
 }

 @Bean
 public SchedulerFactoryBean schedulerFactoryBean(DataSource dataSource, JobFactory jobFactory,
 Trigger sampleJobTrigger) throws IOException {
 SchedulerFactoryBean factory = new SchedulerFactoryBean();
 // this allows to update triggers in DB when updating settings in config file:
 factory.setOverwriteExistingJobs(true);
 factory.setDataSource(dataSource);
 factory.setJobFactory(jobFactory);

 factory.setQuartzProperties(quartzProperties());
 factory.setTriggers(sampleJobTrigger);

 return factory;
 }

 @Bean
 public Properties quartzProperties() throws IOException {
 PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
 propertiesFactoryBean.setLocation(new ClassPathResource("/application.properties"));
 propertiesFactoryBean.afterPropertiesSet();
 return propertiesFactoryBean.getObject();
 }

 @Bean
 public JobDetailFactoryBean sampleJobDetail() {
 return createJobDetail(SampleJob.class);
 }

 @Bean(name = "sampleJobTrigger")
 public SimpleTriggerFactoryBean sampleJobTrigger(JobDetail jobDetail,
 @Value("${samplejob.frequency}") long frequency) {
 return createTrigger(jobDetail, frequency);
 }

 private static JobDetailFactoryBean createJobDetail(Class jobClass) {
 JobDetailFactoryBean factoryBean = new JobDetailFactoryBean();
 factoryBean.setJobClass(jobClass);
 // job has to be durable to be stored in DB:
 factoryBean.setDurability(true);
 return factoryBean;
 }

 private static SimpleTriggerFactoryBean createTrigger(JobDetail jobDetail, long pollFrequencyMs) {
 SimpleTriggerFactoryBean factoryBean = new SimpleTriggerFactoryBean();
 factoryBean.setJobDetail(jobDetail);
 factoryBean.setStartDelay(0L);
 factoryBean.setRepeatInterval(pollFrequencyMs);
 factoryBean.setRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY);
 // in case of misfire, ignore all missed triggers and continue :
 factoryBean.setMisfireInstruction(SimpleTrigger.MISFIRE_INSTRUCTION_RESCHEDULE_NEXT_WITH_REMAINING_COUNT);
 return factoryBean;
 }


* */





































































