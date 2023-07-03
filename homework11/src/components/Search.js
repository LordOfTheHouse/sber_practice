import React, { useState } from 'react';

export const SearchBar = ({ productList, setProductItems }) => {
    const [searchQuery, setSearchQuery] = useState('');

    const handleSearch = () => {
        if (productList) {
            const filteredProducts = productList.filter(product =>
                product.name.toLowerCase().includes(searchQuery.toLowerCase())
            );
            setProductItems(filteredProducts);
        }
    };

    const handleChange = (event) => {
        setSearchQuery(event.target.value);
    };

    return (
        <div className="input-group d-flex justify-content-center" style={{ width: "300px", margin: "auto" }}>
            <input
                type="text"
                className="form-control"
                placeholder="Search"
                maxLength="20"
                value={searchQuery}
                onChange={handleChange}
            />
            <div className="input-group-append">
                <button className="btn btn-outline-secondary" type="button" onClick={handleSearch}>
                    Search
                </button>
            </div>
        </div>
    );
};