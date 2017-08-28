package com.bitwise.pagination.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.bitwise.pagination.web.beans.Phone;
import com.bitwise.pagination.web.service.PhoneService;

import work.CustomPageable;
import work.Loader;

import java.util.ArrayList;
import java.util.List;
public class DemoCustomPageable extends CustomPageable<Long, Phone> {

    private final int size;
    @Autowired
    public DemoCustomPageable(int size) {
        this.size = size;
    }

    public Page<Phone> find(long index, Pageable pageable, List<Phone> list) {
        Loader<Phone> loader = () -> {

            /*for (int i = 0; i < size; i++) {
                list.addAll(list);
            }*/
            return list;
        };

        return find(index, pageable, loader);
    }
}
