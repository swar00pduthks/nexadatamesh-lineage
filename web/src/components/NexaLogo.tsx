import React from 'react'

interface NexaLogoProps {
  size?: number
  className?: string
}

export const NexaLogo: React.FC<NexaLogoProps> = ({ size = 40, className = '' }) => {
  return (
    <svg 
      xmlns="http://www.w3.org/2000/svg" 
      viewBox="0 0 32 32" 
      width={size} 
      height={size}
      className={className}
    >
      {/* White background */}
      <rect x="0" y="0" width="32" height="32" fill="#FFFFFF"/>
      
      {/* Black rounded square */}
      <rect x="4" y="4" width="24" height="24" rx="6" fill="#000000" stroke="#000000" strokeWidth="1"/>
      
      {/* Central white dot */}
      <circle cx="16" cy="16" r="2.5" fill="#FFFFFF"/>
      
      {/* Four outer white dots at midpoints */}
      <circle cx="16" cy="8" r="1.8" fill="#FFFFFF"/>   {/* Top */}
      <circle cx="16" cy="24" r="1.8" fill="#FFFFFF"/>   {/* Bottom */}
      <circle cx="8" cy="16" r="1.8" fill="#FFFFFF"/>    {/* Left */}
      <circle cx="24" cy="16" r="1.8" fill="#FFFFFF"/>   {/* Right */}
      
      {/* Connection lines from center to outer dots */}
      <line x1="16" y1="13.5" x2="16" y2="9.8" stroke="#FFFFFF" strokeWidth="2"/>  {/* Top line */}
      <line x1="16" y1="18.5" x2="16" y2="22.2" stroke="#FFFFFF" strokeWidth="2"/>  {/* Bottom line */}
      <line x1="13.5" y1="16" x2="9.8" y2="16" stroke="#FFFFFF" strokeWidth="2"/>  {/* Left line */}
      <line x1="18.5" y1="16" x2="22.2" y2="16" stroke="#FFFFFF" strokeWidth="2"/>  {/* Right line */}
      
      {/* X-pattern lines connecting corner dots */}
      <line x1="8" y1="8" x2="24" y2="24" stroke="#FFFFFF" strokeWidth="1.5"/>  {/* Top-left to bottom-right */}
      <line x1="24" y1="8" x2="8" y2="24" stroke="#FFFFFF" strokeWidth="1.5"/>  {/* Top-right to bottom-left */}
    </svg>
  )
} 