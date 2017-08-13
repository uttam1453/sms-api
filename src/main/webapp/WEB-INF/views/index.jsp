<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="shortcut icon" type="image/png" href="fevicon.ico"/>
    <title>Server Running</title>
    <style>
        /*
            CSS animated loading dots
        */
        html, body {
            height: 100%;
            margin: 0 auto;
        }

        body {
            display: table;
            color: #c6972e;
        }

        .server_img {
            width: 300px;
        }

        .loading_dots {
            /*
              position just for demo
            */
            margin: 20px auto;
        }

        .loading_dots span {
            background: transparent;
            border-radius: 50%;
            box-shadow: inset 0 0 1px rgba(0, 0, 0, 0.3);
            display: inline-block;
            height: 0.6em;
            width: 0.6em;

            -webkit-animation: loading_dots 0.8s linear infinite;
            -moz-animation: loading_dots 0.8s linear infinite;
            -ms-animation: loading_dots 0.8s linear infinite;
            animation: loading_dots 0.8s linear infinite;
        }

        .loading_dots span:nth-child(2) {
            -webkit-animation-delay: 0.2s;
            -moz-animation-delay: 0.2s;
            -ms-animation-delay: 0.2s;
            animation-delay: 0.2s;
        }

        .loading_dots span:nth-child(1) {
            -webkit-animation-delay: 0.4s;
            -moz-animation-delay: 0.4s;
            -ms-animation-delay: 0.4s;
            animation-delay: 0.4s;
        }

        /*
          Define the animation for every efing merchant prefix
        */
        @-webkit-keyframes loading_dots {
            0% {
                background: transparent;
            }
            50% {
                background: #c6972e;
            }
            100% {
                background: #88BA56;
            }
        }

        @-moz-keyframes loading_dots {
            0% {
                background: transparent;
            }
            50% {
                background: #c6972e;
            }
            100% {
                background: #88BA56;
            }
        }

        @-ms-keyframes loading_dots {
            0% {
                background: transparent;
            }
            50% {
                background: #c6972e;
            }
            100% {
                background: #88BA56;
            }
        }

        @keyframes loading_dots {
            0% {
                background: transparent;
            }
            50% {
                background: #c6972e;
            }
            100% {
                background: #88BA56;
            }
        }

        .center_body {
            text-align: center;
            font-size: 24px;
            display: table-cell;
            vertical-align: middle;
        }
    </style>
</head>
<body class="background">
<div class="center_body" id="stripe_connecting">
    <div class="loading_dots">
    	<h2>Turtle Phase 2</h2><br>
        Server is up & running
        <span></span>
        <span></span>
        <span></span>
    </div>
</div>
</body>
</html>