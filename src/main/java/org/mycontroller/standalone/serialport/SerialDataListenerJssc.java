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
package org.mycontroller.standalone.serialport;

import org.mycontroller.standalone.ObjectFactory;
import org.mycontroller.standalone.mysensors.RawMessage;
import org.mycontroller.standalone.mysensors.RawMessageException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

/**
 * @author Jeeva Kandasamy (jkandasa)
 * @since 0.0.1
 */
public class SerialDataListenerJssc implements SerialPortEventListener {
    private static Logger _logger = LoggerFactory.getLogger(SerialDataListenerJssc.class.getName());
    SerialPort serialPort;

    public SerialDataListenerJssc(SerialPort serialPort) {
        this.serialPort = serialPort;
    }

    StringBuilder message = new StringBuilder();

    public void serialEvent(SerialPortEvent event) {
        if (event.isRXCHAR() && event.getEventValue() > 0) {
            try {
                byte buffer[] = serialPort.readBytes();
                for (byte b : buffer) {
                    if ((b == '\r' || b == '\n') && message.length() > 0) {
                        String toProcess = message.toString();
                        _logger.debug("Received a message:[{}]", toProcess);
                        //Send Message to message factory
                        ObjectFactory.getRawMessageQueue().putMessage(new RawMessage(toProcess));
                        message.setLength(0);
                    }
                    else {
                        if (b != '\n') {
                            _logger.trace("Received a char:[{}]", ((char) b));
                            message.append((char) b);
                        }
                    }
                }
            } catch (SerialPortException ex) {
                _logger.error("Serail Event Exception, ", ex);
            } catch (RawMessageException rEx) {
                _logger.warn(rEx.getMessage());
                message.setLength(0);
            }

        }
    }

}
