import React, { useState, useEffect } from 'react';
import { View, Text, TextInput, Button, StyleSheet, ActivityIndicator } from 'react-native';
import { useDispatch, useSelector } from 'react-redux';
import { loginUser } from './redux-toolkit/userSlice';

const LoginScreen = ({ navigation }) => {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const dispatch = useDispatch();
    const { isLoggedIn, status, error } = useSelector((state) => state.user);

    const handleLogin = () => {
        dispatch(loginUser({ email, password }));
    };

    // Chuyển hướng sang BikeList nếu đăng nhập thành công
    useEffect(() => {
        if (isLoggedIn) {
            navigation.navigate('BikeList');
        }
    }, [isLoggedIn]);

    return (
        <View style={styles.container}>
            <Text style={styles.title}>Login</Text>
            <TextInput
                style={styles.input}
                placeholder="Email"
                value={email}
                onChangeText={setEmail}
            />
            <TextInput
                style={styles.input}
                placeholder="Password"
                value={password}
                secureTextEntry
                onChangeText={setPassword}
            />
            <Button title="Login" onPress={handleLogin} disabled={status === 'loading'} />
            {status === 'loading' && <ActivityIndicator />}
            {error && <Text style={styles.error}>{error}</Text>}
            <Button title="Go to Register" onPress={() => navigation.navigate('Register')} />
        </View>
    );
};

const styles = StyleSheet.create({
    container: {
        flex: 1,
        padding: 16,
        justifyContent: 'center',
    },
    title: {
        fontSize: 24,
        marginBottom: 16,
        textAlign: 'center',
    },
    input: {
        height: 40,
        borderColor: 'gray',
        borderWidth: 1,
        marginBottom: 12,
        paddingHorizontal: 8,
    },
    error: {
        color: 'red',
        marginTop: 8,
    },
});

export default LoginScreen;
