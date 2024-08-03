// src/NamespaceComponent.tsx
import React, { useEffect, useState } from 'react';
import { io, Socket } from 'socket.io-client';

interface NamespaceComponentProps {
  namespace: string;
}

const NamespaceComponent: React.FC<NamespaceComponentProps> = ({ namespace }) => {
  const [message, setMessage] = useState<string>('');
  const [receivedMessages, setReceivedMessages] = useState<string[]>([]);
  const [socket, setSocket] = useState<Socket | null>(null);

  useEffect(() => {
    const newSocket = io(`http://localhost:9092/${namespace}`, {
      transports: ['websocket'],  // 强制使用 WebSocket
    });
    setSocket(newSocket);

    newSocket.on('connect', () => {
      console.log(`Connected to /${namespace} namespace`);
    });

    newSocket.on('message', (data: string) => {
      console.log(`Message received in /${namespace} namespace: `, data);
      setReceivedMessages((prevMessages) => [...prevMessages, data]);
    });

    newSocket.on('disconnect', () => {
      console.log(`Disconnected from /${namespace} namespace`);
    });

    return () => {
      newSocket.disconnect();
    };
  }, [namespace]);

  const sendMessage = () => {
    if (message.trim() !== '' && socket) {
      socket.emit('message', message);
      setMessage('');
    }
  };

  return (
    <div>
      <h1>Namespace {namespace}</h1>
      <input
        type="text"
        value={message}
        onChange={(e) => setMessage(e.target.value)}
        placeholder="Enter your message"
      />
      <button onClick={sendMessage}>Send Message</button>
      <div>
        <h2>Received Messages</h2>
        <ul>
          {receivedMessages.map((msg, index) => (
            <li key={index}>{msg}</li>
          ))}
        </ul>
      </div>
    </div>
  );
};

export default NamespaceComponent;
