package com.plumdo.flow.rest.model.resource;

import java.util.List;

import org.flowable.bpmn.model.BpmnModel;
import org.flowable.editor.language.json.converter.BpmnJsonConverter;
import org.flowable.validation.ProcessValidator;
import org.flowable.validation.ProcessValidatorFactory;
import org.flowable.validation.ValidationError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * 模型检查接口
 *
 * @author wengwh
 * @date 2018/12/6
 */
@RestController
public class ModelValidationResource {

    @PostMapping(value = "/models/validate", name = "模型检查")
    public List<ValidationError> validate(@RequestBody JsonNode body) {
        BpmnModel bpmnModel = new BpmnJsonConverter().convertToBpmnModel(body);
        ProcessValidator validator = new ProcessValidatorFactory().createDefaultProcessValidator();
        return validator.validate(bpmnModel);
    }

}