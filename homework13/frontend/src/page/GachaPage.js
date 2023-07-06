import React, { useState } from 'react';
import { Button, Card } from 'antd';
import { CSSTransition } from 'react-transition-group';
import './GachaPage.css';
import rose from "../img/rose2.jpg";
import van from "../img/van.jpg";
import billy from "../img/billy.jpg";
import put from "../img/put.jpeg";
import svet from "../img/svet.jpg";
import team from "../img/team.jpg";
import sasha from "../img/sasha.jpg";
import kruti from "../img/Gacha-Life-Logo.png";

export const GachaPage = () => {
    const [showCard, setShowCard] = useState(false);
    const [randomImage, setRandomImage] = useState(null);

    const images = [
        rose,
        sasha,
        van,
        billy,
        put,
        svet,
        team
    ];

    const handleButtonClick = () => {
        if (!showCard) {
            setShowCard(true);
            setRandomImage(images[Math.floor(Math.random() * images.length)]);
        }
    };

    const handleCloseClick = () => {
        setShowCard(false);
    };

    return (
        <div style={{ backgroundImage: `url(${kruti})`, backgroundSize: 'cover', minHeight: '100vh' }}>
            <div style={{ display: 'flex', justifyContent: 'center', padding: '100px 0' }}>
                <Button onClick={handleButtonClick} type="primary" size="large">
                    Крутить
                </Button>
            </div>
            <div style={{ display: 'flex', justifyContent: 'center' }}>
                <CSSTransition
                    in={showCard}
                    timeout={1000}
                    classNames="card"
                    unmountOnExit
                >
                    <div className="card-container">
                        <Card
                            title={<span style={{ color: '#ff8c00', textShadow: '2px 2px 4px rgba(0, 0, 0, 0.5)', fontSize: '32px'  }}>Легенда</span>}
                            style={{ width: 280, textAlign: 'center' }}
                            className="card-content"
                        >
                            <p>Бесценно</p>
                            {randomImage && (
                                <img src={randomImage} alt="Изображение товара" style={{ maxWidth: '100%', maxHeight: '200px', margin: '0 auto' }} />
                            )}
                            <Button onClick={handleCloseClick} type="primary">Просвятиться</Button>
                        </Card>
                    </div>
                </CSSTransition>
            </div>
        </div>
    );
};
