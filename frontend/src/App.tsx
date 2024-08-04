// src/App.tsx
import React from 'react';
import NamespaceComponent from './components/NamespaceComponent';
import './App.css';
import DraggableContainer from './components/DraggableContainer';

const App: React.FC = () => {
  return (
    <div className="App">
      <DraggableContainer id="componentA">
        <NamespaceComponent namespace='frontend' />
      </DraggableContainer>
      <DraggableContainer id="componentB">
        <NamespaceComponent namespace='backup' />
      </DraggableContainer>
    </div>
  );
};

export default App;
