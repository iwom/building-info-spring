package com.builder.demo.model.impl;

import com.builder.demo.model.Location;
import com.builder.demo.model.Visitor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LocationVisitor implements Visitor {
    @Override
    public void visit(Location location) {
        log.info("Location was successfully created: " + location.toString());
    }
}
