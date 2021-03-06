/*
 * Copyright 2002-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */
package sample.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.saml.provider.provisioning.SamlProviderProvisioning;
import org.springframework.security.saml.provider.service.ServiceProviderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Controller
public class ServiceProviderController {

	private static final Log logger =LogFactory.getLog(ServiceProviderController.class);
	private SamlProviderProvisioning<ServiceProviderService> provisioning;

	@Autowired
	public void setSamlService(SamlProviderProvisioning<ServiceProviderService> provisioning) {
		this.provisioning = provisioning;
	}

	@RequestMapping(value = {"/", "/index", "/logged-in"})
	public String home(Authentication authentication) 
	{
		logger.info("Sample SP Application - You are logged in!");
		String name = authentication.getName();
		logger.info(name);
		return "logged-in";
	}

}
