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
package org.mycontroller.standalone.db.tables;

import com.j256.ormlite.field.DatabaseField;

/**
 * @author Jeeva Kandasamy (jkandasa)
 * @since 0.0.1
 */
public class MetricsOnOffTypeDevice {
    public static final String SENSOR_REF_ID = "sensor_ref_id";
    public static final String TIMESTAMP = "timestamp";

    @DatabaseField(canBeNull = false, foreign = true, uniqueCombo = true, columnName = SENSOR_REF_ID)
    private Sensor sensor;
    @DatabaseField(uniqueCombo = true, canBeNull = false)
    private Long timestamp;
    @DatabaseField(canBeNull = false)
    private Boolean state;

    private Long timestampFrom;
    private Long timestampTo;

    public MetricsOnOffTypeDevice() {

    }

    public MetricsOnOffTypeDevice(Sensor sensor, Long timestamp) {
        this(sensor, timestamp, null);
    }

    public MetricsOnOffTypeDevice(Sensor sensor, Long timestamp, Boolean state) {
        this.sensor = sensor;
        this.state = state;
        this.timestamp = timestamp;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public Long getTimestampFrom() {
        return timestampFrom;
    }

    public void setTimestampFrom(Long timestampFrom) {
        this.timestampFrom = timestampFrom;
    }

    public Long getTimestampTo() {
        return timestampTo;
    }

    public void setTimestampTo(Long timestampTo) {
        this.timestampTo = timestampTo;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}