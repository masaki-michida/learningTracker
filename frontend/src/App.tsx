// src/App.tsx
import React from 'react';
import AuthPage from './pages/AuthPage';
import LearningRecordPage from './pages/LearningRecordPage';
import { AuthProvider, useAuth } from './contexts/AuthContext';

const AppContent: React.FC = () => {
    const { isAuthenticated, login } = useAuth();

    if (!isAuthenticated) {
        return <AuthPage onLogin={login} />;
    }

    return <LearningRecordPage />;
};

const App: React.FC = () => {
    return (
        <AuthProvider>
            <div className="container mx-auto px-4">
                <h1 className="text-3xl font-bold text-center my-8">学習記録アプリ</h1>
                <AppContent />
            </div>
        </AuthProvider>
    );
};

export default App;