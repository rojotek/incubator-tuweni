/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.tuweni.devp2p.v5.packet

import org.apache.tuweni.bytes.Bytes
import org.junit.jupiter.api.Test

class FindNodeMessageTest {

  @Test
  fun encodeCreatesValidBytesSequence() {
    val expectedEncodingResult = "0xCA88C6E32C5E89CAA75480"

    val requestId = Bytes.fromHexString("0xC6E32C5E89CAA754")
    val message = FindNodeMessage(requestId)

    val encodingResult = message.encode()
    assert(encodingResult.toHexString() == expectedEncodingResult)

    val decodingResult = FindNodeMessage.create(encodingResult)

    assert(decodingResult.requestId == requestId)
    assert(decodingResult.distance == 0)
  }

  @Test
  fun getMessageTypeHasValidIndex() {
    val message = FindNodeMessage()

    assert(3 == message.getMessageType().toInt())
  }
}
