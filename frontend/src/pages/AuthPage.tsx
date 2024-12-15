import React, { useState } from 'react';
import LoginForm from '../components/LoginForm';
import RegisterForm from '../components/RegisterForm';

interface AuthPageProps {
    onLogin: (token: string) => void;
}

const AuthPage: React.FC<AuthPageProps> = ({ onLogin }) => {
    const [isLogin, setIsLogin] = useState(true);

    return (
        <div className="max-w-md mx-auto mt-8">
            <div className="flex justify-center mb-4">
                <button
                    onClick={() => setIsLogin(true)}
                    className={`px-4 py-2 mr-2 rounded ${
                        isLogin ? 'bg-blue-500 text-white' : 'bg-gray-200'
                    }`}
                >
                    ログイン
                </button>
                <button
                    onClick={() => setIsLogin(false)}
                    className={`px-4 py-2 rounded ${
                        !isLogin ? 'bg-blue-500 text-white' : 'bg-gray-200'
                    }`}
                >
                    新規登録
                </button>
            </div>
            {isLogin ? (
                <LoginForm onLogin={onLogin} />
            ) : (
                <RegisterForm onRegisterSuccess={() => setIsLogin(true)} />
            )}
        </div>
    );
};

export default AuthPage; 