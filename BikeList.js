import React, { useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { fetchAll, addBikes, updateBikes, deleteBikes } from './redux-toolkit/bikeSlice';
import { View, Text, TextInput, Button, FlatList, TouchableOpacity, StyleSheet } from 'react-native';

const BikeList = ({ navigation }) => {
  const bikes = useSelector((state) => state.bikes.data);
  const dispatch = useDispatch();
  const [newBike, setNewBike] = useState({
    name: '', price: '', image: '', type: ''
  });
  const [editBikes, setEditBikes] = useState(null);

  useEffect(() => {
    dispatch(fetchAll());
  }, [dispatch]);

  const handleAdd = () => {
    if (newBike.name && newBike.price && newBike.image && newBike.type) {
      dispatch(addBikes(newBike));
      setNewBike({ name: '', price: '', image: '', type: '' });
    }
  };

  const handleEdit = (bike) => {
    setEditBikes(bike);
  };

  const handleUpdate = () => {
    if (editBikes) {
      dispatch(updateBikes({ id: editBikes.id, bikedata: editBikes }));
      setEditBikes(null);
    }
  };

  const handleDelete = (id) => {
    dispatch(deleteBikes(id));
  };

  return (
    <View style={styles.container}>
      <Text style={styles.title}>Danh sách xe</Text>

      {/* Form Add New Bike */}
      <View style={styles.formContainer}>
        <TextInput
          style={styles.input}
          placeholder="Name"
          value={newBike.name}
          onChangeText={(text) => setNewBike({ ...newBike, name: text })}
        />
        <TextInput
          style={styles.input}
          placeholder="Price"
          keyboardType="numeric"
          value={newBike.price}
          onChangeText={(text) => setNewBike({ ...newBike, price: text })}
        />
        <TextInput
          style={styles.input}
          placeholder="Image URL"
          value={newBike.image}
          onChangeText={(text) => setNewBike({ ...newBike, image: text })}
        />
        <TextInput
          style={styles.input}
          placeholder="Type"
          value={newBike.type}
          onChangeText={(text) => setNewBike({ ...newBike, type: text })}
        />
        <Button title="Add Bike" onPress={handleAdd} />
      </View>

      {/* Edit Bike Form */}
      {editBikes && (
        <View style={styles.formContainer}>
          <Text style={styles.subTitle}>Edit Bike</Text>
          <TextInput
            style={styles.input}
            placeholder="Name"
            value={editBikes.name}
            onChangeText={(text) => setEditBikes({ ...editBikes, name: text })}
          />
          <TextInput
            style={styles.input}
            placeholder="Price"
            keyboardType="numeric"
            value={editBikes.price}
            onChangeText={(text) => setEditBikes({ ...editBikes, price: text })}
          />
          <TextInput
            style={styles.input}
            placeholder="Image URL"
            value={editBikes.image}
            onChangeText={(text) => setEditBikes({ ...editBikes, image: text })}
          />
          <TextInput
            style={styles.input}
            placeholder="Type"
            value={editBikes.type}
            onChangeText={(text) => setEditBikes({ ...editBikes, type: text })}
          />
          <Button title="Update Bike" onPress={handleUpdate} />
        </View>
      )}

      {/* List of Bikes */}
      <FlatList
        data={bikes}
        renderItem={({ item }) => (
          <View style={styles.bikeItem}>
            <Text style={styles.bikeName}>{item.name}</Text>
            <Text style={styles.bikePrice}>${item.price}</Text>
            <Text style={styles.bikeImage}>{item.image}</Text>
            <Text style={styles.bikeType}>{item.type}</Text>
            <TouchableOpacity onPress={() => handleEdit(item)}>
              <Text style={styles.button}>Edit</Text>
            </TouchableOpacity>
            <TouchableOpacity onPress={() => handleDelete(item.id)}>
              <Text style={styles.button}>Delete</Text>
            </TouchableOpacity>
          </View>
        )}
        keyExtractor={(item) => item.id.toString()}
      />
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    padding: 20,
    flex: 1,
    backgroundColor: '#f5f5f5'
  },
  title: {
    fontSize: 24,
    fontWeight: 'bold',
    marginBottom: 20
  },
  formContainer: {
    marginBottom: 20
  },
  input: {
    height: 40,
    borderColor: '#ccc',
    borderWidth: 1,
    borderRadius: 5,
    marginBottom: 10,
    paddingHorizontal: 10
  },
  subTitle: {
    fontSize: 18,
    fontWeight: 'bold',
    marginBottom: 10
  },
  bikeItem: {
    backgroundColor: '#fff',
    padding: 15,
    marginBottom: 10,
    borderRadius: 5,
    shadowColor: '#000',
    shadowOffset: { width: 0, height: 2 },
    shadowOpacity: 0.8,
    shadowRadius: 2,
    elevation: 3
  },
  bikeName: {
    fontSize: 18,
    fontWeight: 'bold'
  },
  bikePrice: {
    fontSize: 16,
    color: 'green'
  },
  bikeImage: {
    fontSize: 14,
    color: 'blue'
  },
  bikeType: {
    fontSize: 14,
    color: 'gray'
  },
  button: {
    color: 'blue',
    marginVertical: 5
  }
});

export default BikeList;
