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
import org.apache.tuweni.rlp.RLP

class RegTopicMessage(
  val requestId: Bytes = UdpMessage.requestId(),
  val nodeRecord: Bytes,
  val topic: Bytes,
  val ticket: Bytes
) : UdpMessage() {

  private val encodedMessageType: Bytes = Bytes.fromHexString("0x05")

  override fun getMessageType(): Bytes = encodedMessageType

  override fun encode(): Bytes {
    return RLP.encodeList { writer ->
      writer.writeValue(requestId)
      writer.writeValue(nodeRecord)
      writer.writeValue(topic)
      writer.writeValue(ticket)
    }
  }

  companion object {
    fun create(content: Bytes): RegTopicMessage {
      return RLP.decodeList(content) { reader ->
        val requestId = reader.readValue()
        val nodeRecord = reader.readValue()
        val topic = reader.readValue()
        val ticket = reader.readValue()
        return@decodeList RegTopicMessage(requestId, nodeRecord, topic, ticket)
      }
    }
  }
}
