package com.epam.service;

import com.epam.model.Mark;

public class MarkServer {
    public Mark getMark() {
        return new Mark(10);
    }
}
