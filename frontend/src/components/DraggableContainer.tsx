// DraggableContainer.tsx
import React, { useState, useEffect } from 'react';
import Draggable from 'react-draggable';

interface DraggableContainerProps {
  id: string;
  children: React.ReactNode;
}

const DraggableContainer: React.FC<DraggableContainerProps> = ({ id, children }) => {
  const [position, setPosition] = useState({ x: 0, y: 0 });

  useEffect(() => {
    const savedPosition = localStorage.getItem(`draggablePosition_${id}`);
    if (savedPosition) {
      setPosition(JSON.parse(savedPosition));
    }
  }, [id]);

  const handleDrag = (e: any, data: any) => {
    setPosition({ x: data.x, y: data.y });
  };

  const handleStop = () => {
    localStorage.setItem(`draggablePosition_${id}`, JSON.stringify(position));
  };

  return (
    <Draggable position={position} onDrag={handleDrag} onStop={handleStop}>
      <div className='draggable-card'>
        {children}
      </div>
    </Draggable>
  );
};

export default DraggableContainer;
