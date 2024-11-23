import React from 'react';
import { Link } from 'react-router-dom';
import { Twitter, Facebook, Instagram, Github, Linkedin, Mail } from 'lucide-react';

const Footer = () => {
  return (
    <footer className="bg-white dark:bg-slate-900 border-t border-gray-100 dark:border-slate-800 py-16">
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div className="grid grid-cols-1 md:grid-cols-4 gap-12">
          {/* Company Info */}
          <div className="space-y-6">
            <h3 className="text-xl font-light text-gray-900 dark:text-white tracking-wide">
              OceanVista
            </h3>
            <div className="space-y-4">
              <p className="text-sm text-gray-600 dark:text-gray-300 leading-relaxed font-light">
                Pioneering innovative digital solutions with a vision to transform technological landscapes.
              </p>
              <div className="flex space-x-4">
                {[
                  { Icon: Twitter, href: "https://twitter.com/oceanvista" },
                  { Icon: Facebook, href: "https://facebook.com/oceanvista" },
                  { Icon: Instagram, href: "https://instagram.com/oceanvista" },
                  { Icon: Github, href: "https://github.com/oceanvista" },
                  { Icon: Linkedin, href: "https://linkedin.com/company/oceanvista" }
                ].map(({ Icon, href }) => (
                  <a 
                    key={href} 
                    href={href} 
                    className="text-gray-400 dark:text-gray-500 
                      transition-colors duration-300 
                      hover:text-gray-600 dark:hover:text-gray-300"
                  >
                    <Icon size={20} strokeWidth={1.5} />
                  </a>
                ))}
              </div>
            </div>
          </div>

          {/* Quick Links */}
          <div className="space-y-6">
            <h3 className="text-xl font-light text-gray-900 dark:text-white tracking-wide">
              Quick Links
            </h3>
            <ul className="space-y-3">
              {[
                { label: "Home", path: "/" },
                { label: "About", path: "/about" },
                { label: "Pricing", path: "/pricing" },
                { label: "Contact", path: "/contact" }
              ].map(({ label, path }) => (
                <li key={path}>
                  <Link 
                    to={path} 
                    className="text-gray-500 dark:text-gray-400 
                      transition-colors duration-300 
                      hover:text-gray-700 dark:hover:text-gray-200"
                  >
                    {label}
                  </Link>
                </li>
              ))}
            </ul>
          </div>

          {/* Resources */}
          <div className="space-y-6">
            <h3 className="text-xl font-light text-gray-900 dark:text-white tracking-wide">
              Resources
            </h3>
            <ul className="space-y-3">
              {[
                { label: "Blog", href: "/blog" },
                { label: "Documentation", href: "/documentation" },
                { label: "Help Center", href: "#" },
                { label: "API Reference", href: "#" }
              ].map(({ label, href }) => (
                <li key={label}>
                  <a 
                    href={href} 
                    className="text-gray-500 dark:text-gray-400 
                      transition-colors duration-300 
                      hover:text-gray-700 dark:hover:text-gray-200"
                  >
                    {label}
                  </a>
                </li>
              ))}
            </ul>
          </div>

          {/* Contact Info */}
          <div className="space-y-6">
            <h3 className="text-xl font-light text-gray-900 dark:text-white tracking-wide">
              Contact
            </h3>
            <ul className="space-y-4">
              <li className="flex items-center space-x-3">
                <Mail size={18} className="text-gray-400 dark:text-gray-500" />
                <span className="text-gray-500 dark:text-gray-400 
                  transition-colors duration-300 
                  hover:text-gray-700 dark:hover:text-gray-200">
                  contact@oceanvista.co.in
                </span>
              </li>
              <li>
                <p className="text-gray-500 dark:text-gray-400 leading-relaxed font-light">
                  No.75, Hesaraghatta Rd,<br />
                  Near Sambram College,<br />
                  Vidyaranyapura, Bengaluru<br />
                  Karnataka 560095
                </p>
              </li>
            </ul>
          </div>
        </div>

        {/* Bottom Section */}
        <div className="mt-16 pt-8 border-t border-gray-100 dark:border-slate-800">
          <div className="flex flex-col md:flex-row justify-between items-center space-y-4 md:space-y-0">
            <p className="text-sm text-gray-500 dark:text-gray-400 font-light">
              © {new Date().getFullYear()} OceanVista. All rights reserved.
            </p>
            <div className="flex space-x-6">
              {[
                { label: "Privacy Policy", href: "#" },
                { label: "Terms of Service", href: "#" },
                { label: "Cookie Policy", href: "#" }
              ].map(({ label, href }) => (
                <a 
                  key={label} 
                  href={href} 
                  className="text-sm text-gray-500 dark:text-gray-400 
                    transition-colors duration-300 
                    hover:text-gray-700 dark:hover:text-gray-200"
                >
                  {label}
                </a>
              ))}
            </div>
          </div>
        </div>
      </div>
    </footer>
  );
};

export default Footer;