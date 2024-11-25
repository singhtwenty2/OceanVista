package com.singhtwenty2.OceanVistaBusiness.util.template;

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

                        * {
                            margin: 0;
                            padding: 0;
                            box-sizing: border-box;
                        }

                        body {
                            font-family: 'Inter', sans-serif;
                            background: linear-gradient(135deg, #f4f7fa 0%%, #e9ecef 100%%);
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
                            animation: fadeIn 0.6s ease-out;
                        }

                        .content {
                            background: rgba(255, 255, 255, 0.95);
                            backdrop-filter: blur(10px);
                            border-radius: 24px;
                            padding: 48px 32px;
                            max-width: 580px;
                            margin: 0 auto;
                            box-shadow: 0 20px 48px rgba(0, 0, 0, 0.1);
                            border: 1px solid rgba(0, 113, 227, 0.15);
                            text-align: center;
                            transform: translateY(0);
                            transition: transform 0.3s ease, box-shadow 0.3s ease;
                        }

                        .content:hover {
                            transform: translateY(-2px);
                            box-shadow: 0 24px 52px rgba(0, 0, 0, 0.12);
                        }

                        .icon {
                            width: 72px;
                            height: 72px;
                            margin-bottom: 28px;
                            animation: scaleIn 0.5s ease-out 0.3s both;
                        }

                        .success .icon {
                            color: #00c853;
                        }

                        .error .icon {
                            color: #f44336;
                        }

                        .header {
                            font-size: 32px;
                            font-weight: 700;
                            margin-bottom: 20px;
                            letter-spacing: -0.03em;
                            animation: slideUp 0.5s ease-out 0.4s both;
                        }

                        .success .header {
                            color: #00c853;
                        }

                        .error .header {
                            color: #f44336;
                        }

                        .message {
                            font-size: 17px;
                            line-height: 1.7;
                            color: #4a5568;
                            margin-bottom: 36px;
                            padding: 0 24px;
                            animation: slideUp 0.5s ease-out 0.5s both;
                        }

                        .button {
                            display: inline-block;
                            padding: 16px 36px;
                            background: linear-gradient(135deg, #0071e3 0%%, #005bbf 100%%);
                            color: #ffffff !important;
                            text-decoration: none;
                            border-radius: 12px;
                            font-weight: 600;
                            font-size: 16px;
                            transition: all 0.3s ease;
                            animation: slideUp 0.5s ease-out 0.6s both;
                            box-shadow: 0 4px 12px rgba(0, 113, 227, 0.2);
                        }

                        .button:hover {
                            background: linear-gradient(135deg, #005bbf 0%%, #004a9f 100%%);
                            transform: translateY(-2px);
                            box-shadow: 0 6px 16px rgba(0, 113, 227, 0.25);
                        }

                        @keyframes fadeIn {
                            from { opacity: 0; }
                            to { opacity: 1; }
                        }

                        @keyframes slideUp {
                            from {
                                opacity: 0;
                                transform: translateY(20px);
                            }
                            to {
                                opacity: 1;
                                transform: translateY(0);
                            }
                        }

                        @keyframes scaleIn {
                            from {
                                opacity: 0;
                                transform: scale(0.8);
                            }
                            to {
                                opacity: 1;
                                transform: scale(1);
                            }
                        }

                        @media (max-width: 640px) {
                            .container {
                                padding: 24px;
                            }
                            
                            .content {
                                padding: 36px 24px;
                            }

                            .header {
                                font-size: 28px;
                            }

                            .message {
                                padding: 0;
                                font-size: 16px;
                            }
                        }

                        @media (prefers-color-scheme: dark) {
                            body {
                                background: linear-gradient(135deg, #1a1f36 0%%, #151928 100%%);
                                color: #ffffff;
                            }

                            .content {
                                background: rgba(26, 31, 54, 0.95);
                                border-color: rgba(255, 255, 255, 0.1);
                            }

                            .message {
                                color: #cbd5e0;
                            }

                            .button {
                                background: linear-gradient(135deg, #0080ff 0%%, #0066cc 100%%);
                            }

                            .button:hover {
                                background: linear-gradient(135deg, #0066cc 0%%, #004d99 100%%);
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
                        ? "http://192.168.0.155:5173/login"
                        : "/resend-verification",

                isVerified
                        ? "Continue to Login"
                        : "Resend Verification Email"
        );
    }
}