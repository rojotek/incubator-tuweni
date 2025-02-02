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
package org.apache.tuweni.devp2p.v5.storage

import org.apache.tuweni.bytes.Bytes
import org.apache.tuweni.crypto.Hash
import org.apache.tuweni.devp2p.v5.ENRStorage
import java.util.concurrent.ConcurrentHashMap

class DefaultENRStorage : ENRStorage {

  private val storage: MutableMap<String, Bytes> = ConcurrentHashMap()

  override fun find(nodeId: Bytes): Bytes? = storage[nodeId.toHexString()]

  override fun set(enr: Bytes) {
    val nodeId = Hash.sha2_256(enr)
    storage[nodeId.toHexString()] = enr
  }
}
