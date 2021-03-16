<html>
<#-- @ftlvariable name="data" type="io.qameta.allure.attachment.http.HttpRequestAttachment" -->

    <head>
        <meta http-equiv="content-type" content="text/html; charset = UTF-8">
        <script src="https://yastatic.net/jquery/2.2.3/jquery.min.js" crossorigin="anonymous"></script>

        <link href="https://yastatic.net/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet"
            crossorigin="anonymous">
        <script src="https://yastatic.net/bootstrap/3.3.6/js/bootstrap.min.js" crossorigin="anonymous"></script>

        <link type="text/css" href="https://yandex.st/highlightjs/8.0/styles/github.min.css" rel="stylesheet" />
        <script type="text/javascript" src="https://yandex.st/highlightjs/8.0/highlight.min.js"></script>
        <script type="text/javascript" src="https://yandex.st/highlightjs/8.0/languages/bash.min.js"></script>
        <script type="text/javascript" src="https://yandex.st/highlightjs/8.0/languages/json.min.js"></script>
        <script type="text/javascript" src="https://yandex.st/highlightjs/8.0/languages/xml.min.js"></script>
        <script type="text/javascript">hljs.initHighlightingOnLoad();</script>
        <style>
            pre {
                white-space: pre-wrap;
            }

            .custom {
                margin: 0 10px;
            }

            .custom:first-of-type {
                margin: 10 10 7 10px;
            }

            h4 {
                margin: 0 10 7 10px;
            }
        </style>
    </head>

    <body>

            <div class="card card-body">
                <h4>Url</h4>
                <pre><code><#if data.method??>${data.method}<#else>GET</#if>: <#if data.url??>${data.url}<#else>Unknown</#if></code></pre>
                <pre><code>${data.url}</code></pre>
            </div>

            <div class="card card-body">
                <h4>Headers</h4>
                <#if (data.headers)?has_content>
                    <#list data.headers as name, value>
                        <pre><code><b>${name}</b>: ${value}</code></pre>
                    </#list>
                </#if>
            </div>

            <div class="card card-body">
                <h4>Body</h4>
                <#if data.body??>
                    <pre><code>${data.body}</code></pre>
                </#if>
        </div>
    </body>

</html>