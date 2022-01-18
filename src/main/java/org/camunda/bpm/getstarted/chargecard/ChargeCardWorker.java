package org.camunda.bpm.getstarted.chargecard;

import java.util.logging.Logger;
import java.awt.Desktop;
import java.net.URI;
import com.hassan.service.CreateEmployee;

import org.camunda.bpm.client.ExternalTaskClient;

public class ChargeCardWorker {
  private final static Logger LOGGER = Logger.getLogger(ChargeCardWorker.class.getName());

  public static void main(String[] args) {
    ExternalTaskClient client = ExternalTaskClient.create()
        .baseUrl("http://localhost:8080/engine-rest")
        .asyncResponseTimeout(10000) // long polling timeout
        .build();

    // subscribe to an external task topic as specified in the process
    client.subscribe("entering-record")
        .lockDuration(1000) // the default lock duration is 20 seconds, but you can override this
        .handler((externalTask, externalTaskService) -> {
          // Put your business logic here

          // Get a process variable
          
          String name = (String) externalTask.getVariable("name");
          int salary = (int) externalTask.getVariable("salary");
          String degree = (String) externalTask.getVariable("degree");
          new CreateEmployee().createEmployee(name,salary,degree);
        	
          LOGGER.info("Data fetched from the form is name = "+name+" salary = "+salary+" degree = "+degree);

          // Complete the task
          externalTaskService.complete(externalTask);
        })
        .open();
  }
}