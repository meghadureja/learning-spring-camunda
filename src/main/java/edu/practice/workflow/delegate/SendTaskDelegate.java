package edu.practice.workflow.delegate;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import static edu.practice.workflow.constants.Constants.IS_CLIENT_ACCEPTED;

@Component
@Slf4j
public class SendTaskDelegate implements JavaDelegate {
  @Override
  public void execute(DelegateExecution delegateExecution) throws Exception {
    log.info("Inside SendTaskDelegate");
    delegateExecution.setVariable(IS_CLIENT_ACCEPTED,"Yes");
  }
}
