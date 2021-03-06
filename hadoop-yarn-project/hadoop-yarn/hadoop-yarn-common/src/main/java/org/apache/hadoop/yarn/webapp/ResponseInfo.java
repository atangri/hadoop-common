/**
* Licensed to the Apache Software Foundation (ASF) under one
* or more contributor license agreements.  See the NOTICE file
* distributed with this work for additional information
* regarding copyright ownership.  The ASF licenses this file
* to you under the Apache License, Version 2.0 (the
* "License"); you may not use this file except in compliance
* with the License.  You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package org.apache.hadoop.yarn.webapp;

import com.google.common.collect.Lists;
import com.google.inject.servlet.RequestScoped;

import java.util.Iterator;
import java.util.List;

/**
 * A class to help passing around request scoped info
 */
@RequestScoped
public class ResponseInfo implements Iterable<ResponseInfo.Item> {

  public static class Item {
    public final String key;
    public final String url;
    public final Object value;

    Item(String key, String url, Object value) {
      this.key = key;
      this.url = url;
      this.value = value;
    }

    public static Item of(String key, Object value) {
      return new Item(key, null, value);
    }

    public static Item of(String key, String url, Object value) {
      return new Item(key, url, value);
    }
  }

  final List<Item> items = Lists.newArrayList();
  String about = "Info";

  // Do NOT add any constructors here, unless...

  public static ResponseInfo $about(String about) {
    ResponseInfo info = new ResponseInfo();
    info.about = about;
    return info;
  }

  public ResponseInfo about(String about) {
    this.about = about;
    return this;
  }

  public String about() {
    return about;
  }

  public ResponseInfo _(String key, Object value) {
    items.add(Item.of(key, value));
    return this;
  }

  public ResponseInfo _(String key, String url, Object anchor) {
    items.add(Item.of(key, url, anchor));
    return this;
  }

  public void clear() {
    items.clear();
  }

  @Override
  public Iterator<Item> iterator() {
    return items.iterator();
  }
}
