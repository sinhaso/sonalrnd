package com.sonal.streamncompfut.main;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.sonal.streamncompfut.service.SampleService;
import com.sonal.streamncompfut.vo.ServiceRequestVO;

public class Entrypoint {

    public static void main(String[] args) {

	SampleService sampleService = new SampleService();

	List<ServiceRequestVO> serviceRequests = buildServiceRequests();
	
	serviceRequests.parallelStream()
		       .map(sr -> sampleService.doSomething(sr))
		       .map(cf -> cf.thenApplyAsync(sampleServiceResponse -> sampleServiceResponse.getResult()))
		       .collect(Collectors.toList())
		       .forEach(resultCF -> resultCF.whenComplete(Entrypoint :: receiveResult)
			       			    .join());

    }
    
    public static void receiveResult(String result, Throwable t){
	System.out.println("received :: " + result);
    }
   
    public static List<ServiceRequestVO> buildServiceRequests() {
	
	List<ServiceRequestVO>  serviceRequestVOs = new ArrayList<>();
	
	IntStream.rangeClosed(1, 10)
		 .forEach(i -> serviceRequestVOs.add(buildServiceRequest(i)));
	
	return serviceRequestVOs;
    }
    
    public static ServiceRequestVO buildServiceRequest(int i) {
	ServiceRequestVO serviceRequestVO = new ServiceRequestVO();
	serviceRequestVO.setInput("Job " + i);
	return serviceRequestVO;
    }

}