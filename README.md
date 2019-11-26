

# AgentStart

简化跳转 `Activity` 的工具类，无需设置 `requestCode` 和重写 `onActivityResult` 方法。

## 使用方法

1.普通跳转：

```java
AgentStart.with(MainActivity.this).start(FirstActivity.class);
```

2.普通跳转（带数据）：

```java
AgentStart.with(MainActivity.this).start(FirstActivity.class, intent);
```

3.接收返回结果的跳转：

```java
AgentStart.with(MainFragment.this)
        .setResultListener(new ResultListener() {
            @Override
            public void onResult(int resultCode, Intent data) {
                // TODO
            }
        })
        .startForResult(FirstActivity.class);
```

4.接收返回结果的跳转（带数据）：

```java
AgentStart.with(MainFragment.this)
        .setResultListener(new ResultListener() {
            @Override
            public void onResult(int resultCode, Intent data) {
                // TODO
            }
        })
        .startForResult(FirstActivity.class, intent);
```



## 已知问题

上一层接收返回结果的 `Activity` 如果因为屏幕旋转或者系统内存不足被销毁，那么设置的 `ResultListener` 也会重新创建，导致无法接收 `setResult` 方法返回的数据，如果有这方面的需求，最好还是通过重写 `onActivityResult` 方法实现。

## License

```
MIT License

Copyright (c) 2019 zerdaket

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
