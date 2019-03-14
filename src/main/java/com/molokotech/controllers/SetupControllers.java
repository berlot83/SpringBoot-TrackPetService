package com.molokotech.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.molokotech.model.User;
import com.molokotech.service.UserService;

@RestController
public class SetupControllers {

	@Autowired
	UserService userService;

	@GetMapping("/lock-templates")
	public void lockTemplates(@RequestParam boolean locked) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUser(auth.getName());
		user.getSetup().setLockTemplate(locked);
		userService.saveUser(user);
	}

	@GetMapping("/molokotech-connect")
	public void molokotechConnect(@RequestParam boolean showQuickConnectMolokotech) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUser(auth.getName());
		user.getSetup().setShowQuickConnectMolokotech(showQuickConnectMolokotech);
		userService.saveUser(user);
	}

	@GetMapping("/share-buttons")
	public void shareButtons(@RequestParam boolean showSharebuttons) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUser(auth.getName());
		user.getSetup().setShowSharebuttons(showSharebuttons);
		userService.saveUser(user);
	}

	@GetMapping("/enable-coordinates")
	public void enableCoordinates(@RequestParam boolean sendCoordinates) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUser(auth.getName());
		user.getSetup().setSendCoordinates(sendCoordinates);
		userService.saveUser(user);
	}
	
	
	@GetMapping("/show-special-message")
	public void showSpecialMessage(@RequestParam boolean showSpecialMessage) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUser(auth.getName());
		user.getSetup().setShowSpecialMessage(showSpecialMessage);
		userService.saveUser(user);
	}

	@GetMapping("/show-special-inputs")
	public void showSpecialInputs(@RequestParam boolean showSpecialInputs) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUser(auth.getName());
		user.getSetup().setShowSpecialInputs(showSpecialInputs);
		userService.saveUser(user);
	}
	

	@GetMapping("/show-map")
	public void showMap(@RequestParam boolean showMap) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUser(auth.getName());
		user.getSetup().setShowMap(showMap);
		userService.saveUser(user);
	}
	

	@GetMapping("/show-footer")
	public void showFooter(@RequestParam boolean showFooter) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUser(auth.getName());
		user.getSetup().setShowFooter(showFooter);
		userService.saveUser(user);
	}

	@GetMapping("/show-youtube-video")
	public void showYoutubeVideo(@RequestParam boolean showYoutubeVideo) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUser(auth.getName());
		user.getSetup().setShowYoutubeVideo(showYoutubeVideo);
		userService.saveUser(user);
	}
}
