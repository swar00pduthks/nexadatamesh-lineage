import { useState, useEffect } from 'react'
import { Database, GitBranch, BarChart3, ArrowLeft, Sun, Moon } from 'lucide-react'
import { NamespacesList } from './components/NamespacesList'
import { DatasetsList } from './components/DatasetsList'
import { LineageGraph } from './components/LineageGraph'

type View = 'dashboard' | 'namespaces' | 'datasets' | 'graph'

function App() {
  const [currentView, setCurrentView] = useState<View>('dashboard')
  const [darkMode, setDarkMode] = useState(false)

  useEffect(() => {
    // Check for saved theme preference or default to light mode
    const savedTheme = localStorage.getItem('theme')
    if (savedTheme === 'dark' || (!savedTheme && window.matchMedia('(prefers-color-scheme: dark)').matches)) {
      setDarkMode(true)
    }
  }, [])

  useEffect(() => {
    // Apply theme to document
    if (darkMode) {
      document.documentElement.classList.add('dark')
      localStorage.setItem('theme', 'dark')
    } else {
      document.documentElement.classList.remove('dark')
      localStorage.setItem('theme', 'light')
    }
  }, [darkMode])

  const toggleTheme = () => {
    setDarkMode(!darkMode)
  }

  const renderContent = () => {
    switch (currentView) {
      case 'namespaces':
        return <NamespacesList />
      case 'datasets':
        return <DatasetsList />
      case 'graph':
        return <LineageGraph />
      default:
        return (
          <>
            <div className="grid grid-cols-1 md:grid-cols-3 gap-6 mb-8">
              <div className="bg-white dark:bg-gray-800 p-6 rounded-lg shadow-sm border border-gray-200 dark:border-gray-700">
                <div className="flex items-center mb-4">
                  <Database className="w-6 h-6 text-gray-700 dark:text-gray-300 mr-3" />
                  <h3 className="text-lg font-semibold text-gray-900 dark:text-white">Namespaces</h3>
                </div>
                <p className="text-gray-600 dark:text-gray-400 mb-4">
                  Manage data namespaces and their metadata
                </p>
                <button 
                  onClick={() => setCurrentView('namespaces')}
                  className="bg-gray-900 dark:bg-white text-white dark:text-gray-900 px-4 py-2 rounded hover:bg-gray-800 dark:hover:bg-gray-100 transition-colors"
                >
                  View Namespaces
                </button>
              </div>

              <div className="bg-white dark:bg-gray-800 p-6 rounded-lg shadow-sm border border-gray-200 dark:border-gray-700">
                <div className="flex items-center mb-4">
                  <GitBranch className="w-6 h-6 text-gray-700 dark:text-gray-300 mr-3" />
                  <h3 className="text-lg font-semibold text-gray-900 dark:text-white">Datasets</h3>
                </div>
                <p className="text-gray-600 dark:text-gray-400 mb-4">
                  Explore datasets and their lineage relationships
                </p>
                <button 
                  onClick={() => setCurrentView('datasets')}
                  className="bg-gray-900 dark:bg-white text-white dark:text-gray-900 px-4 py-2 rounded hover:bg-gray-800 dark:hover:bg-gray-100 transition-colors"
                >
                  View Datasets
                </button>
              </div>

              <div className="bg-white dark:bg-gray-800 p-6 rounded-lg shadow-sm border border-gray-200 dark:border-gray-700">
                <div className="flex items-center mb-4">
                  <BarChart3 className="w-6 h-6 text-gray-700 dark:text-gray-300 mr-3" />
                  <h3 className="text-lg font-semibold text-gray-900 dark:text-white">Lineage Graph</h3>
                </div>
                <p className="text-gray-600 dark:text-gray-400 mb-4">
                  Visualize data lineage and impact analysis
                </p>
                <button 
                  onClick={() => setCurrentView('graph')}
                  className="bg-gray-900 dark:bg-white text-white dark:text-gray-900 px-4 py-2 rounded hover:bg-gray-800 dark:hover:bg-gray-100 transition-colors"
                >
                  View Graph
                </button>
              </div>
            </div>

            <div className="bg-white dark:bg-gray-800 p-6 rounded-lg shadow-sm border border-gray-200 dark:border-gray-700">
              <h2 className="text-2xl font-semibold mb-4 text-gray-900 dark:text-white">API Status</h2>
              <div className="flex items-center space-x-4">
                <div className="flex items-center">
                  <div className="w-3 h-3 bg-green-500 rounded-full mr-2"></div>
                  <span className="text-sm text-gray-600 dark:text-gray-400">API Connected</span>
                </div>
                <div className="flex items-center">
                  <div className="w-3 h-3 bg-green-500 rounded-full mr-2"></div>
                  <span className="text-sm text-gray-600 dark:text-gray-400">PostgreSQL</span>
                </div>
                <div className="flex items-center">
                  <div className="w-3 h-3 bg-green-500 rounded-full mr-2"></div>
                  <span className="text-sm text-gray-600 dark:text-gray-400">Neo4j</span>
                </div>
              </div>
            </div>
          </>
        )
    }
  }

  return (
    <div className="min-h-screen bg-gray-50 dark:bg-gray-900 transition-colors duration-200">
      <div className="container mx-auto px-4 py-8">
        <header className="mb-8">
          <div className="flex items-center justify-between mb-4">
            <div className="flex items-center">
              {currentView !== 'dashboard' && (
                <button 
                  onClick={() => setCurrentView('dashboard')}
                  className="flex items-center text-gray-600 dark:text-gray-300 hover:text-gray-900 dark:hover:text-white mr-4 transition-colors"
                >
                  <ArrowLeft className="w-4 h-4 mr-2" />
                  Back to Dashboard
                </button>
              )}
                              <div className="flex items-center">
                  {/* Nexa DataMesh Logo */}
                  <div className="w-10 h-10 bg-gray-900 dark:bg-white rounded-lg flex items-center justify-center mr-4">
                    <div className="w-6 h-6 relative">
                      {/* Network nodes */}
                      <div className="absolute top-0 left-0 w-1.5 h-1.5 bg-white dark:bg-gray-900 rounded-full"></div>
                      <div className="absolute top-0 right-0 w-1.5 h-1.5 bg-white dark:bg-gray-900 rounded-full"></div>
                      <div className="absolute bottom-0 left-0 w-1.5 h-1.5 bg-white dark:bg-gray-900 rounded-full"></div>
                      <div className="absolute bottom-0 right-0 w-1.5 h-1.5 bg-white dark:bg-gray-900 rounded-full"></div>
                      <div className="absolute top-1/2 left-1/2 transform -translate-x-1/2 -translate-y-1/2 w-1.5 h-1.5 bg-white dark:bg-gray-900 rounded-full"></div>
                      {/* Network lines */}
                      <div className="absolute top-0.5 left-0.5 w-3 h-px bg-white dark:bg-gray-900 transform rotate-45 origin-left"></div>
                      <div className="absolute top-0.5 right-0.5 w-3 h-px bg-white dark:bg-gray-900 transform -rotate-45 origin-right"></div>
                      <div className="absolute bottom-0.5 left-0.5 w-3 h-px bg-white dark:bg-gray-900 transform -rotate-45 origin-left"></div>
                      <div className="absolute bottom-0.5 right-0.5 w-3 h-px bg-white dark:bg-gray-900 transform rotate-45 origin-right"></div>
                    </div>
                  </div>
                  <h1 className="text-4xl font-bold text-gray-900 dark:text-white">
                    Nexa Lineage
                  </h1>
              </div>
            </div>
            <button
              onClick={toggleTheme}
              className="p-2 rounded-lg bg-gray-100 dark:bg-gray-800 text-gray-600 dark:text-gray-300 hover:bg-gray-200 dark:hover:bg-gray-700 transition-colors"
            >
              {darkMode ? <Sun className="w-5 h-5" /> : <Moon className="w-5 h-5" />}
            </button>
          </div>
          <p className="text-gray-600 dark:text-gray-400">
            Track data flow across your entire data ecosystem
          </p>
        </header>

        {renderContent()}
      </div>
    </div>
  )
}

export default App 