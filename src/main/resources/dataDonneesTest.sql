-- Insertion dans la table `entreprise`
INSERT INTO `entreprise` (`nom`) VALUES
('Entreprise A'),
('Entreprise B'),
('Entreprise C');

-- Insertion dans la table `convention`
INSERT INTO `convention` (`entreprise_id`, `salaire_maximum`, `subvention`, `nom`) VALUES
(1, 3000, 500.0, 'Convention A'),
(2, 2500, 300.0, 'Convention B'),
(3, 2800, 450.0, 'Convention C');

-- Insertion dans la table `utilisateur`
INSERT INTO `utilisateur` (`entreprise_id`, `email`, `password`, `role`) VALUES
(1, 'user1@entrepriseA.com', 'azerty', 'ADMIN'),
(1, 'user2@entrepriseA.com', 'azerty', 'USER'),
(2, 'user3@entrepriseB.com', 'azerty', 'USER'),
(3, 'user4@entrepriseC.com', 'azerty', 'ADMIN');

-- Insertion dans la table `salarie`
INSERT INTO `salarie` (`convention_id`, `conventions_id`, `matricule`, `code_barre`) VALUES
(1, 1, 'SAL001', '1234567890'),
(2, 2, 'SAL002', '0987654321'),
(3, 3, 'SAL003', '1122334455');

