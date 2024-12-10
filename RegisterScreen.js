import React, { useState, useEffect } from 'react';
import { View, Text, TextInput, Button, StyleSheet, ActivityIndicator } from 'react-native';
import { useDispatch, useSelector } from 'react-redux';
import { registerUser } from './redux-toolkit/userSlice';

const RegisterScreen = ({ navigation }) => {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [successMessage, setSuccessMessage] = useState('');
    const dispatch = useDispatch();
    const { status, error } = useSelector((state) => state.user);

    const handleRegister = () => {
        dispatch(registerUser({ email, password }))
            .unwrap()
            .then(() => {
                setSuccessMessage('Registration successful!');
                setTimeout(() => {
                    setSuccessMessage('');
                    navigation.navigate('Login');
                }, 2000); // Hiển thị thông báo trong 2 giây
            })
            .catch(() => { }); // Xử lý lỗi trong `error` từ Redux
    };

    return (
        <View style={styles.container}>
            <Text style={styles.title}>Register</Text>
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
            <Button title="Register" onPress={handleRegister} disabled={status === 'loading'} />
            {status === 'loading' && <ActivityIndicator />}
            {error && <Text style={styles.error}>{error}</Text>}
            {successMessage && <Text style={styles.success}>{successMessage}</Text>}
            <Button title="Go to Login" onPress={() => navigation.navigate('Login')} />
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
    success: {
        color: 'green',
        marginTop: 8,
        fontSize: 16,
        textAlign: 'center',
    },
});

export default RegisterScreen;
