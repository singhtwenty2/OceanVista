package com.singhtwenty2.OceanVista.util.template;

public class EmailVerificationTemplate {
    public static String emailVerificationHTML(String verificationLink) {
        return String.format("""
                <html>
                <head>
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <style>
                        @import url('https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&display=swap');
                
                        body {
                            font-family: 'Inter', sans-serif;
                            background-color: #f4f7fa;
                            color: #1a1f36;
                            margin: 0;
                            padding: 0;
                            -webkit-font-smoothing: antialiased;
                        }
                        .container {
                            width: 100%%;
                            padding: 40px 20px;
                            text-align: center;
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
                            position: relative;
                            text-align: center;
                        }
                        .header {
                            font-size: 28px;
                            font-weight: 700;
                            color: #0071e3;
                            margin-bottom: 32px;
                            text-align: center;
                            letter-spacing: -0.03em;
                        }
                        .welcome-text {
                            font-size: 18px;
                            font-weight: 600;
                            color: #1a1f36;
                            margin-bottom: 20px;
                        }
                        p {
                            font-size: 16px;
                            line-height: 1.8;
                            color: #1a1f36;
                            margin: 0 0 24px;
                            letter-spacing: -0.01em;
                        }
                        .steps {
                            text-align: left;
                            background: #f8fafc;
                            padding: 20px 24px;
                            border-radius: 12px;
                            margin: 24px 0;
                        }
                        .steps-title {
                            font-weight: 600;
                            margin-bottom: 12px;
                            color: #0071e3;
                        }
                        .steps ul {
                            margin: 0;
                            padding-left: 20px;
                        }
                        .steps li {
                            margin-bottom: 8px;
                            line-height: 1.6;
                        }
                        .verification-link {
                            display: inline-block;
                            padding: 14px 32px;
                            background-color: #0071e3;
                            color: #ffffff !important;
                            text-decoration: none;
                            border-radius: 8px;
                            font-weight: 500;
                            margin: 24px 0;
                            transition: all 0.2s ease;
                        }
                        .verification-link:hover {
                            background-color: #005bbf;
                            transform: translateY(-1px);
                        }
                        .notice {
                            font-size: 14px;
                            color: #666;
                            margin: 32px 0;
                            padding: 16px;
                            background-color: #f8fafc;
                            border-radius: 12px;
                        }
                        .footer {
                            font-size: 14px;
                            color: #666;
                            margin-top: 32px;
                            text-align: center;
                            border-top: 1px solid #eee;
                            padding-top: 20px;
                        }
                        
                        @media (max-width: 640px) {
                            .content {
                                padding: 32px 20px;
                            }
                            .header {
                                font-size: 24px;
                            }
                            .verification-link {
                                display: block;
                                margin: 24px 20px;
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
                            .welcome-text, p, .steps li {
                                color: #e5e7eb;
                            }
                            .steps {
                                background-color: #232a45;
                            }
                            .notice {
                                background-color: #232a45;
                                color: #e5e7eb;
                            }
                            .footer {
                                color: #e5e7eb;
                                border-top-color: #2e3648;
                            }
                        }
                    </style>
                </head>
                <body>
                    <div class="container">
                        <div class="content">
                            <div class="header">Complete Your Registration</div>
                            
                            <div class="welcome-text">Welcome to OceanVista!</div>
                            
                            <p>Thank you for creating an account with us. We're excited to have you on board!</p>
                            
                            <div class="steps">
                                <div class="steps-title">Complete these simple steps:</div>
                                <ul>
                                    <li>Click the verification button below</li>
                                    <li>Your email will be verified instantly</li>
                                    <li>You'll be redirected to complete your profile</li>
                                </ul>
                            </div>
                            
                            <p>To ensure the security of your account and activate all features, please verify your email address now:</p>
                            
                            <a href="%s" class="verification-link">Verify Email Address</a>
                            
                            <div class="notice">
                                For security purposes, this verification link will expire in 30 minutes.
                                <br><br>
                                If you didn't create an OceanVista account, please disregard this email or contact our support team.
                            </div>
                            
                            <div class="footer">
                                &copy; 2024 OceanVista. All rights reserved.
                                <br>
                                This is an automated message, please do not reply to this email.
                            </div>
                        </div>
                    </div>
                </body>
                </html>
                """, verificationLink);
    }
}