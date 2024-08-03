// src/App.tsx
import React from 'react';
import NamespaceComponent from './components/NamespaceComponent';
import './App.css';

const App: React.FC = () => {
  return (
    <div className="App">
      <div className="left">
        <NamespaceComponent namespace="frontend" />
      </div>
      <div className="right">
        <NamespaceComponent namespace="backup" />
      </div>
    </div>
  );
};

export default App;
