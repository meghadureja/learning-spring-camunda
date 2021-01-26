package edu.practice.workflow.controller;

import edu.practice.workflow.domain.ServiceDTO;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.variable.Variables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static edu.practice.workflow.constants.Constants.*;

@RestController
public class WorkflowController {

  @Autowired
  RuntimeService runtimeService;

  @PostMapping("/processes/{clientId}")
  @ResponseBody
  public String startWorkflowProcess(@RequestBody ServiceDTO serviceDTO, @PathVariable("clientId") Long clientId) {
    ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(PROCESS_REQ_TO_DELIVERY,
      Variables.putValue(SERVICE_DTO, serviceDTO)
        .putValue(CLIENT_ID, clientId));
    return processInstance.getProcessInstanceId();
  }

}
