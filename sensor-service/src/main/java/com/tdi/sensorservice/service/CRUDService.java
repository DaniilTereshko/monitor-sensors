package com.tdi.sensorservice.service;

public interface CRUDService<E, R, ID> {

    E getById(ID id);

    E create(R request);

    void delete(ID id);

    E update(R request);

}