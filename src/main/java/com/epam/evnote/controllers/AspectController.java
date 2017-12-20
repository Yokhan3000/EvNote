package com.epam.evnote.controllers;

import com.epam.evnote.aspects.ServiceMonitor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Mikhail Chtetsov on 20/12/2017.
 */
@RestController
public class AspectController {

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(value = "/switcher")
  public void switcher(){
    boolean onOff = ServiceMonitor.isON();
    ServiceMonitor.setON(!onOff);
    System.err.println("*******************" + ServiceMonitor.isON());
  }
}
