package com.singhtwenty2.OceanVista.util.template;

public class WebPage {
    public static String EmailVerificationPage(boolean isVerified) {
        return String.format("""
                <!DOCTYPE html>
                <html>
                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <style>
                        @import url('https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&display=swap');

                        body {
                            font-family: 'Inter', sans-serif;
                            background-color: #f4f7fa;
                            color: #1a1f36;
                            margin: 0;
                            padding: 0;
                            min-height: 100vh;
                            display: flex;
                            align-items: center;
                            justify-content: center;
                            -webkit-font-smoothing: antialiased;
                        }

                        .container {
                            width: 100%%;
                            padding: 40px 20px;
                            box-sizing: border-box;
                        }

                        .content {
                            background: linear-gradient(to bottom right, #ffffff, #fafbfc);
                            border-radius: 20px;
                            padding: 48px 24px;
                            max-width: 580px;
                            margin: 0 auto;
                            box-shadow: 0 12px 40px rgba(0, 0, 0, 0.08);
                            border: 1px solid rgba(0, 113, 227, 0.1);
                            text-align: center;
                        }

                        .icon {
                            width: 64px;
                            height: 64px;
                            margin-bottom: 24px;
                        }

                        .success .icon {
                            color: #34c759;
                        }

                        .error .icon {
                            color: #ff3b30;
                        }

                        .header {
                            font-size: 28px;
                            font-weight: 700;
                            margin-bottom: 16px;
                            letter-spacing: -0.03em;
                        }

                        .success .header {
                            color: #34c759;
                        }

                        .error .header {
                            color: #ff3b30;
                        }

                        .message {
                            font-size: 16px;
                            line-height: 1.6;
                            color: #1a1f36;
                            margin-bottom: 32px;
                            padding: 0 24px;
                        }

                        .button {
                            display: inline-block;
                            padding: 14px 32px;
                            background-color: #0071e3;
                            color: #ffffff !important;
                            text-decoration: none;
                            border-radius: 8px;
                            font-weight: 500;
                            transition: all 0.2s ease;
                        }

                        .button:hover {
                            background-color: #005bbf;
                            transform: translateY(-1px);
                        }

                        @media (max-width: 640px) {
                            .container {
                                padding: 20px;
                            }
                            
                            .content {
                                padding: 32px 20px;
                            }

                            .header {
                                font-size: 24px;
                            }

                            .message {
                                padding: 0;
                                font-size: 15px;
                            }
                        }

                        @media (prefers-color-scheme: dark) {
                            body {
                                background-color: #1a1f36;
                                color: #ffffff;
                            }

                            .content {
                                background: linear-gradient(to bottom right, #1a1f36, #232a45);
                                border-color: rgba(255, 255, 255, 0.1);
                            }

                            .message {
                                color: #e5e7eb;
                            }
                        }
                    </style>
                </head>
                <body>
                    <div class="container">
                        <div class="content %s">
                            <svg class="icon" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                                %s
                            </svg>
                            <div class="header">%s</div>
                            <div class="message">
                                %s
                            </div>
                            <a href="%s" class="button">%s</a>
                        </div>
                    </div>
                </body>
                </html>
                """,

                isVerified ? "success" : "error",

                isVerified
                        ? "path d='M22 11.08V12a10 10 0 1 1-5.93-9.14' stroke-linecap='round' stroke-linejoin='round'/><path d='M22 4L12 14.01l-3-3' stroke-linecap='round' stroke-linejoin='round'/>"
                        : "circle cx='12' cy='12' r='10' stroke-linecap='round' stroke-linejoin='round'/><path d='M15 9l-6 6M9 9l6 6' stroke-linecap='round' stroke-linejoin='round'/>",

                isVerified
                        ? "Email Verified Successfully!"
                        : "Verification Failed",

                isVerified
                        ? "Your email has been successfully verified. You can now access all features of BlueCompass."
                        : "We couldn't verify your email. This might be because the verification link has expired or has already been used.",

                isVerified
                        ? "/login"
                        : "/resend-verification",

                isVerified
                        ? "Continue to Login"
                        : "Resend Verification Email"
        );
    }
}