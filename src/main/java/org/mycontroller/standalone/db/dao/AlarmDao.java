/**
 * Copyright (C) 2015 Jeeva Kandasamy (jkandasa@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.mycontroller.standalone.db.dao;

import java.util.List;

import org.mycontroller.standalone.db.tables.Alarm;

/**
 * @author Jeeva Kandasamy (jkandasa)
 * @since 0.0.1
 */
public interface AlarmDao {
    void create(Alarm alarm);
    void createOrUpdate(Alarm alarm);
    void delete(Alarm alarm);
    void delete(int id);
    void deleteBySensorRefId(int sensorRefId);
    void update(Alarm alarm);
    List<Alarm> getAll();
    List<Alarm> getAll(int sensorRefId);
    List<Alarm> getAll(int sensorRefId, Boolean enabled);
    Alarm get(int id);
    void disableAllTriggered();
}
