package com.ls.wod.service;

import com.ls.wod.domain.Athlete;
import com.ls.wod.domain.Maximumrepetition;

/**
 *
 * @author francisco
 */

public interface MaximumrepetitionService extends CrudService<Maximumrepetition>{
    Maximumrepetition finByAthlete(Athlete athlete);
}
