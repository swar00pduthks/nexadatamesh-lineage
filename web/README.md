# Nexa Lineage Web UI

Modern React TypeScript frontend for the Nexa Lineage data service.

## 🚀 Quick Start

### Prerequisites
- Node.js 18+
- npm or yarn

### Development

1. **Install dependencies:**
   ```bash
   cd web
   npm install
   ```

2. **Start development server:**
   ```bash
   npm run dev
   ```

3. **Build for production:**
   ```bash
   npm run build
   ```

## 🛠️ Tech Stack

- **React 18** - UI framework
- **TypeScript** - Type safety
- **Vite** - Fast build tool and dev server
- **Tailwind CSS** - Utility-first CSS framework
- **React Router** - Client-side routing
- **React Query** - Server state management
- **Zustand** - Client state management
- **React Flow** - Interactive node-based graphs
- **Recharts** - Data visualization
- **Framer Motion** - Animations
- **Lucide React** - Icons

## 📁 Project Structure

```
web/
├── src/
│   ├── components/     # Reusable UI components
│   ├── pages/         # Page components
│   ├── hooks/         # Custom React hooks
│   ├── services/      # API service functions
│   ├── stores/        # Zustand state stores
│   ├── types/         # TypeScript type definitions
│   ├── utils/         # Utility functions
│   ├── App.tsx        # Main app component
│   ├── main.tsx       # React entry point
│   └── index.css      # Global styles
├── public/            # Static assets
├── package.json       # Dependencies and scripts
├── vite.config.ts     # Vite configuration
├── tsconfig.json      # TypeScript configuration
├── tailwind.config.js # Tailwind CSS configuration
└── index.html         # HTML template
```

## 🔧 Configuration

The development server is configured to proxy API requests to the backend:

```typescript
// vite.config.ts
server: {
  port: 3000,
  proxy: {
    '/api': {
      target: 'http://localhost:8080',
      changeOrigin: true,
    },
  },
}
```

## 🎨 Styling

This project uses Tailwind CSS for styling. The configuration includes:

- Custom color palette
- Responsive design utilities
- Component-based styling approach

## 📦 Build Output

The build process creates optimized production files in the `dist/` directory:

- Minified JavaScript bundles
- Optimized CSS
- Static assets
- Source maps for debugging 