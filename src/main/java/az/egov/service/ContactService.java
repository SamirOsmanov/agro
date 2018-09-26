package az.egov.service;

import az.egov.entity.ActivityAreas;
import az.egov.entity.ContactTypes;

import java.util.List;

/**
 * Created by admin on 26.09.2018.
 */

public interface ContactService  {

    List<ContactTypes> findAll() ;
}
