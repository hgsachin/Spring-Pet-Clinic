package com.hgsachin.springpetclinic.controllers;

import com.hgsachin.springpetclinic.model.Owner;
import com.hgsachin.springpetclinic.service.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class OwnersController {

    private final OwnerService ownerService;

    public OwnersController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @RequestMapping({"owners/all", "owners/index"})
    public String listOwners(Model model) {
        model.addAttribute("owners", ownerService.findAll());
        return "owners/index";
    }

    @GetMapping("owners")
    public String findOwners(Model model, Owner owner, BindingResult result) {
        if (owner.getLastName() == null) {
            owner.setLastName("");
        }
        List<Owner> owners = ownerService.findAllByLastNameLike("%" + owner.getLastName() + "%");
        if (owners.isEmpty()) {
            result.rejectValue("lastName", "notFound", "Not Found");
            return "owners/findOwners";
        } else if (owners.size() == 1) {
            return "redirect:owners/" + owners.get(0).getId();
        } else {
            model.addAttribute("selections", owners);
            return "owners/ownersList";
        }
    }

    @RequestMapping({"/owners/find"})
    public String findOwnerPage(Model model) {
        model.addAttribute("owner", Owner.builder().build());
        return "owners/findOwners";
    }

    @GetMapping("owners/{ownerId}")
    public ModelAndView showOwner(@PathVariable Long ownerId) {
        ModelAndView mav = new ModelAndView("owners/ownerDetails");
        mav.addObject(ownerService.findById(ownerId));
        return mav;
    }

    @GetMapping("owners/new")
    public String newOwnerForm(Model model) {
        model.addAttribute("owner", Owner.builder().build());
        return "owners/createOrUpdateOwnerForm";
    }

    @PostMapping("owners/new")
    public String saveNewOwner(@Valid Owner owner, BindingResult result) {
        if (result.hasErrors()) {
            return "owners/createOrUpdateOwnerForm";
        }
        Owner savedOwner = ownerService.save(owner);
        return "redirect:/owners/" + savedOwner.getId();
    }

    @GetMapping("owners/{ownerId}/edit")
    public String editOwnerForm(@PathVariable Long ownerId, Model model) {
        model.addAttribute("owner", ownerService.findById(ownerId));
        return "owners/createOrUpdateOwnerForm";
    }

    @PostMapping("owners/{ownerId}/edit")
    public String saveEditedOwner(@Valid Owner owner, BindingResult result, @PathVariable Long ownerId) {
        if (result.hasErrors()) {
            return "owners/createOrUpdateOwnerForm";
        }
        owner.setId(ownerId);
        Owner savedOwner = ownerService.save(owner);
        return "redirect:/owners/" + savedOwner.getId();
    }
}
