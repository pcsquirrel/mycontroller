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
package org.mycontroller.standalone.scheduler.jobs;

import org.mycontroller.standalone.db.AGGREGATION_TYPE;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xeiam.sundial.Job;
import com.xeiam.sundial.exceptions.JobInterruptException;

/**
 * @author Jeeva Kandasamy (jkandasa)
 * @since 0.0.1
 */
public class MetricsFiveMinutesAggregationJob extends Job {
    private static final Logger _logger = LoggerFactory.getLogger(MetricsFiveMinutesAggregationJob.class.getName());

    @Override
    public void doRun() throws JobInterruptException {
        _logger.debug("Five Minutes Aggregation job triggered");
        //Do Aggregation for last five minutes and purge
        new MetricsAggregationBase(AGGREGATION_TYPE.FIVE_MINUTES).runAggregate();
        _logger.debug("Five Minutes Aggregation job completed");
    }
}
