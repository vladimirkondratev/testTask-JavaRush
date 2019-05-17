package ru.test.kondratev.controller;

import ru.test.kondratev.model.Component;
import ru.test.kondratev.service.ComponentService;
import java.util.Comparator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ComponentController {

    @Autowired
    private ComponentService componentService;

    @Autowired
    @Qualifier(value = "componentService")
    public void setComponentService(ComponentService componentService) {
        this.componentService = componentService;
    }

    private int filter = 1;
    private int pageNumber = 1;
    private final int COMPONENTS_ON_PAGE = 10;

    @RequestMapping(value = "/components")
    public @ResponseBody
    ModelAndView componentList(@RequestParam(required = false) Integer page,
                               @RequestParam(required = false) Integer filterFlag) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("component", new Component());
        if (filterFlag != null) {
            filter = filterFlag;
        }
        if (page != null) {
            pageNumber = page;
        }
        List<Component> componentList = this.getList(filter);

        if (!componentList.isEmpty()) {
            PagedListHolder<Component> pagedListHolder = new PagedListHolder<>(componentList);
            pagedListHolder.setPageSize(COMPONENTS_ON_PAGE);
            int maxPage = pagedListHolder.getPageCount();
            modelAndView.addObject("maxPage", maxPage);

            if (page == null || page < 1 || page > maxPage) {
                page = pageNumber;
            }
            modelAndView.addObject("page", page);

            if (page > maxPage) {
                pagedListHolder.setPage(page);
            } else if (page <= maxPage) {
                pagedListHolder.setPage(page - 1);
            }

            modelAndView.addObject("componentList", pagedListHolder.getPageList());
            modelAndView.addObject("computers", computersForAssembly());
        } else {
            modelAndView.addObject("componentList", null);
            modelAndView.addObject("computers", 0);
        }
        modelAndView.setViewName("components");
        return modelAndView;
    }

    @RequestMapping(value = "/components/add", method = RequestMethod.POST)
    public String addComponent(@ModelAttribute("component") Component component) {
        if (!component.getName().equals("")) {
            this.componentService.addComponent(component);
        }
        return "redirect:/components";
    }

    @RequestMapping("/components/remove/{id}")
    public String removeCmponent(@PathVariable("id") int id) {
        this.componentService.removeComponent(id);
        return "redirect:/components";
    }

    @RequestMapping(value = "/components/edit/{id}", method = RequestMethod.GET)
    public String editComponent(@PathVariable("id") int id, Model model) {
        model.addAttribute("component", this.componentService.getComponentByID(id));
        return "componentData";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String doEditComponent(@ModelAttribute("component") Component component,
                                  @RequestParam(value = "id") Integer id) {
        if (!component.getName().equals("")) {
            this.componentService.updateComponent(component);
        }
        return "redirect:/components";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public ModelAndView searchUser(@RequestParam() String name) {
        ModelAndView modelAndView = new ModelAndView("/components");
        modelAndView.addObject("component", new Component());
        List<Component> componentList = componentService.getComponentsByName(name);
        componentList.sort(Comparator.comparing(Component::getName));
        modelAndView.addObject("componentList", componentList);
        modelAndView.addObject("computers", computersForAssembly());
        return modelAndView;
    }

    private List<Component> getList(int i) {
        List<Component> result = null;
        switch (i) {
            case 1:
                result = componentService.componentList();
                break;
            case 0:
                result = componentService.componentFilteredList(true);
                break;
            case -1:
                result = componentService.componentFilteredList(false);
                break;
        }
        if (result != null) {
            result.sort(Comparator.comparing(Component::getName));
        }
        return result;
    }

    private int computersForAssembly() {
        List<Component> requiredComponentList = componentService.componentFilteredList(true);
        if (requiredComponentList.size() > 0) {
            requiredComponentList.sort((o1, o2) -> {
                return Integer.compare(o1.getQuantity(), o2.getQuantity());
            });
            return requiredComponentList.get(0).getQuantity();
        }
        return 0;
    }
}
