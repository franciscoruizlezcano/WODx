package com.ls.wod.service;

import com.ls.wod.domain.Phone;

/**
 *
 * @author francisco
 */

public interface PhoneService extends CrudService<Phone>{
    Phone findByPhone(String phone);
}
