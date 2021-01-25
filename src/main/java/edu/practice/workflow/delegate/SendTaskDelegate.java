package edu.practice.workflow.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
public class SendTaskDelegate implements JavaDelegate {
  @Override
  public void execute(DelegateExecution delegateExecution) throws Exception {

  }
}
