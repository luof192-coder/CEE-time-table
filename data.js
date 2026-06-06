const studyDays = [
  // ================= WEEKS 1-2 (DAYS 1 TO 15) — FOUNDATIONS =================
  {
    dayNumber: 1,
    phase: "Foundations (Weeks 1-2)",
    physics: "Units, physical quantities, precision, accuracy, significant figures, and dimensional analysis.",
    chemistry: "Basic concepts: Atoms, molecules, valency, relative atomic mass, molecular mass, and atomic mass unit.",
    zoology: "Animal Tissues & Histology: Epithelial and connective tissues (structure, location, and function).",
    botany: "Basic Components of Life: Structure and biological role of carbohydrates, lipids, and minerals.",
    mat: "Verbal Reasoning: Direction tests & spatial orientation mapping."
  },
  {
    dayNumber: 2,
    phase: "Foundations (Weeks 1-2)",
    physics: "Vectors and scalars: Addition, subtraction, dot product, cross product, and relative velocity.",
    chemistry: "Stoichiometry: Dalton's atomic theory and Laws of stoichiometry (mass conservation, definite proportion).",
    zoology: "Animal Tissues & Histology: Muscular and nervous tissues (structure, location, and function).",
    botany: "Basic Components of Life: Structure, types, and biological roles of proteins and enzymes.",
    mat: "Numerical Reasoning: Number series patterns & mathematical sequences."
  },
  {
    dayNumber: 3,
    phase: "Foundations (Weeks 1-2)",
    physics: "Kinematics 1D: Distance, displacement, speed, velocity, acceleration, and equations of linear motion.",
    chemistry: "Stoichiometry: Avogadro's law and its applications, mole concept, and molar volume calculations.",
    zoology: "Digestive System: Detailed anatomy of human alimentary canal and digestive glands.",
    botany: "Cell Biology: Concept of prokaryotic and eukaryotic cells, cell theory, and basic differences.",
    mat: "Logical Sequencing: Word arrangements & logical ordering of elements."
  },
  {
    dayNumber: 4,
    phase: "Foundations (Weeks 1-2)",
    physics: "Kinematics 2D: Projectile motion calculations (range, maximum height, time of flight, trajectory path).",
    chemistry: "Stoichiometry: Empirical and molecular formula determinations with percentage composition problems.",
    zoology: "Digestive System: Biochemistry and physiology of protein, carbohydrate, and lipid digestion.",
    botany: "Cell Biology: Composition, structure, and functions of the Cell Wall and Cell Membrane.",
    mat: "Spatial Relation: Basic 2D pattern matching & visual symmetry."
  },
  {
    dayNumber: 5,
    phase: "Foundations (Weeks 1-2)",
    physics: "Kinematics: Graphical treatment of linear motion (x-t, v-t, and a-t graphs) and projectile motion under gravity.",
    chemistry: "Stoichiometry: Limiting reactants calculations, percentage yield, and related numerical problems.",
    zoology: "Digestive System: Absorption of digested nutrients, assimilation, defecation, and gastrointestinal disorders.",
    botany: "Cell Biology: Structure and functions of Mitochondria (the cellular powerhouse).",
    mat: "Verbal Reasoning: Analogy tests and word relation systems."
  },
  {
    dayNumber: 6,
    phase: "Foundations (Weeks 1-2)",
    physics: "Nuclear Physics: Nucleus composition, size, mass density, isotopes, isobar, and isotone properties.",
    chemistry: "General Organic Chemistry (GOC): Tetra-covalency and catenation of carbon, and general classification of organic compounds.",
    zoology: "Respiratory System: Anatomy of human respiratory organs (nose, pharynx, larynx, trachea, bronchi, lungs).",
    botany: "Cell Biology: Structure and function of Chloroplasts (the photosynthetic center).",
    mat: "Numerical Reasoning: Numerical fractions & mathematical operations."
  },
  {
    dayNumber: 7,
    phase: "Foundations (Weeks 1-2)",
    physics: "Nuclear Physics: Concept and calculations of mass defect, packing fraction, and conservation in nuclear reactions.",
    chemistry: "GOC: Alkyl and aryl groups, functional groups, and homologous series naming criteria.",
    zoology: "Respiratory System: Physiology of respiration: Pulmonary ventilation, inspiration, and expiration mechanisms.",
    botany: "Cell Biology: Structure and functions of Endoplasmic Reticulum (ER) and Golgi Body.",
    mat: "Logical Sequencing: Coding and decoding logic patterns."
  },
  {
    dayNumber: 8,
    phase: "Foundations (Weeks 1-2)",
    physics: "Nuclear Physics: Binding energy per nucleon calculations, binding energy curve, and nuclear stability.",
    chemistry: "GOC: IUPAC nomenclature of aliphatic hydrocarbons and basic functional compounds.",
    zoology: "Respiratory System: Gas exchange in alveoli: Partial pressures of oxygen and carbon dioxide, diffusion membrane.",
    botany: "Cell Biology: Structure and function of Lysosomes, Ribosomes, and Cell Inclusions.",
    mat: "Spatial Relation: Rotating standard 2D shapes & mental alignment."
  },
  {
    dayNumber: 9,
    phase: "Foundations (Weeks 1-2)",
    physics: "Nuclear Physics: Einstein's mass-energy relation and its application to fission and fusion processes.",
    chemistry: "GOC: Isomerism of organic compounds: Structural isomerism (chain, position, functional, metamerism, tautomerism).",
    zoology: "Respiratory System: Transport of gases: Oxygen transport by hemoglobin and carbon dioxide transport mechanisms.",
    botany: "Cell Biology: Structure and function of the Nucleus, Chromosomes, Cilia, and Flagella.",
    mat: "Verbal Reasoning: Syllogism and logical statement deduction."
  },
  {
    dayNumber: 10,
    phase: "Foundations (Weeks 1-2)",
    physics: "Nuclear Physics: Characteristics of nuclear forces, and comparison between nuclear fission and fusion reactors.",
    chemistry: "GOC: Fission of organic bonds: Heterolytic and homolytic bond fission, electrophile, and nucleophile concepts.",
    zoology: "Respiratory System: Regulation of respiration: Nervous and chemical feedback control, respiratory disorders (asthma, emphysema).",
    botany: "Cell Cycle: Concept of the cell cycle: Interphase stages (G1, S, G2 phase) and regulation.",
    mat: "Numerical Reasoning: Missing term puzzles in grids and matrices."
  },
  {
    dayNumber: 11,
    phase: "Foundations (Weeks 1-2)",
    physics: "Vectors: Lami's theorem, resolution of vectors in 3D, and projectile kinematics on inclined planes.",
    chemistry: "GOC: Reactive intermediates: Formation, structure, and relative stability of Carbocations.",
    zoology: "Digestive System: Digestive enzymes activity curve, dental formula, and tooth histology.",
    botany: "Cell Cycle: Mitosis cell division: Karyokinesis phases (Prophase, Metaphase, Anaphase, Telophase).",
    mat: "Logical Sequencing: Blood relations tree diagrams."
  },
  {
    dayNumber: 12,
    phase: "Foundations (Weeks 1-2)",
    physics: "Mechanics Units: Dimensional formula derivations of constant parameters (gravitational constant, Planck's constant).",
    chemistry: "GOC: Reactive intermediates: Formation, structure, and stability of Carbanions and Free Radicals.",
    zoology: "Animal Tissues: Location and histology of glandular epithelia and cartilages.",
    botany: "Cell Cycle: Cytokinesis in plant and animal cells, and significance of mitosis in growth.",
    mat: "Spatial Relation: Mirror reflections & water reflection images."
  },
  {
    dayNumber: 13,
    phase: "Foundations (Weeks 1-2)",
    physics: "Nuclear energy: Radioactive chain reactions & calculations of critical mass.",
    chemistry: "GOC: Electronic effects: Inductive effect (+I and -I effects) and its application to acidity of acids.",
    zoology: "Human Physiology: Liver physiology, bile composition, and gall bladder functions.",
    botany: "Cell Cycle: Meiosis I (Reductional division) phases: Leptotene, Zygotene, Pachytene, Diplotene, Diakinesis.",
    mat: "Verbal Reasoning: Seating arrangements (linear & circular)."
  },
  {
    dayNumber: 14,
    phase: "Foundations (Weeks 1-2)",
    physics: "Kinematics: Practical relative velocity of boat-river, rain-man vector drift problems.",
    chemistry: "GOC: Electronic effects: Resonance effect and Mesomeric effect (+M and -M effects) with electron density distribution.",
    zoology: "Human Biology: Pancreatic juices, enzymology, and blood supply of intestinal villi.",
    botany: "Cell Cycle: Meiosis II (Equational division) phases, and genetic significance of crossing over in meiosis.",
    mat: "Numerical Reasoning: Group data averages & age calculating problems."
  },
  {
    dayNumber: 15,
    phase: "Foundations (Weeks 1-2)",
    physics: "Review: Foundations Physics numerical challenges (Mechanics, nuclear mass defect calculations).",
    chemistry: "Review: Stoichiometry and GOC key concepts, IUPAC naming, and resonance structures.",
    zoology: "Review: Digestive and Respiratory systems physiology summary and critical diagram labels.",
    botany: "Review: Cell structure, organelles, cell division, and biomolecules structural formulas.",
    mat: "Review: Foundations Reasoning and MAT comprehensive speed practice test."
  },

  // ================= WEEKS 3-4 (DAYS 16 TO 30) — HIGH-YIELD CORE =================
  {
    dayNumber: 16,
    phase: "High-Yield Core (Weeks 3-4)",
    physics: "Dynamics: Newton's laws of motion, inertia, equilibrium of concurrent forces, and free body diagrams.",
    chemistry: "Atomic Structure: Rutherford's alpha scattering experiment, atomic model, limitations.",
    zoology: "Circulatory System: Human heart: External and internal anatomy, chambers, valves, and pericardium.",
    botany: "Genetics: DNA as genetic material, structural composition (Watson-Crick model), and chemical bonds.",
    mat: "Logical Sequencing: Alphabet coding logic and conditional sequences."
  },
  {
    dayNumber: 17,
    phase: "High-Yield Core (Weeks 3-4)",
    physics: "Dynamics: Concept of impulse, momentum conservation, and applications in recoil of guns.",
    chemistry: "Atomic Structure: Bohr's atomic model: Postulates, derivation of radius, and electron velocity.",
    zoology: "Circulatory System: Cardiac cycle phases (atrial/ventricular systole and diastole), heart sounds.",
    botany: "Genetics: RNA types (mRNA, tRNA, rRNA) - structure, functions, and composition differences.",
    mat: "Numerical Reasoning: Work, time, and rate speed problems."
  },
  {
    dayNumber: 18,
    phase: "High-Yield Core (Weeks 3-4)",
    physics: "Dynamics: Collisions: Elastic and inelastic collisions in 1D and 2D, coefficient of restitution.",
    chemistry: "Atomic Structure: Spectrum of hydrogen atom: Lyman, Balmer, Paschen, Brackett, and Pfund series.",
    zoology: "Circulatory System: Cardiac output: Stroke volume, heart rate, regulation of heart rate.",
    botany: "Genetics: DNA Replication: Semiconservative replication mechanism, enzymes (DNA helicase, polymerase, ligase).",
    mat: "Logical Sequencing: Non-verbal visual analogy."
  },
  {
    dayNumber: 19,
    phase: "High-Yield Core (Weeks 3-4)",
    physics: "Rotational Dynamics: Concept of angular displacement, angular velocity, and angular acceleration.",
    chemistry: "Atomic Structure: de-Broglie’s wave equation and dual nature of matter numericals.",
    zoology: "Circulatory System: Blood vessels: Arterial and Venous systems, coronary circulation, Portal systems.",
    botany: "Genetics: Central Dogma: Transcription mechanism, promoter region, mRNA processing.",
    mat: "Spatial Relation: 2D-to-3D net transformations (folding boxes, cube development)."
  },
  {
    dayNumber: 20,
    phase: "High-Yield Core (Weeks 3-4)",
    physics: "Rotational Dynamics: Moment of inertia of a rigid body, radius of gyration, and torque.",
    chemistry: "Atomic Structure: Heisenberg’s uncertainty principles, limitations of classical physics.",
    zoology: "Circulatory System: Blood composition: Plasma, red blood cells, white blood cells, platelets.",
    botany: "Genetics: Central Dogma: Translation mechanism, genetic code, codon-anticodon pairing.",
    mat: "Spatial Relation: Unfolded net identification & 3D projections."
  },
  {
    dayNumber: 21,
    phase: "High-Yield Core (Weeks 3-4)",
    physics: "Rotational Dynamics: Moment of inertia of a rigid uniform rod (derivation about middle and end axes).",
    chemistry: "Atomic Structure: Concept of orbitals, quantum numbers (n, l, m, s), shape of s, p, and d orbitals.",
    zoology: "Circulatory System: Blood groups: ABO system, Rh factor, blood pressure, hypertension, coronary blockages.",
    botany: "Genetics: Mendelian Genetics: Monohybrid cross, Law of Dominance and Law of Segregation.",
    mat: "Logical Sequencing: Logical Venn diagrams & logical deduction."
  },
  {
    dayNumber: 22,
    phase: "High-Yield Core (Weeks 3-4)",
    physics: "Rotational Dynamics: Rotational work, power, kinetic energy of rotation, and angular momentum.",
    chemistry: "Atomic Structure: Rules for electronic configuration: Aufbau principle and Pauli’s exclusion principle.",
    zoology: "Excretory System: Anatomy of the human urinary system: Kidneys, ureters, bladder, urethra.",
    botany: "Genetics: Mendelian Genetics: Dihybrid cross, Law of Independent Assortment.",
    mat: "Numerical Reasoning: Matrix counting & number groupings."
  },
  {
    dayNumber: 23,
    phase: "High-Yield Core (Weeks 3-4)",
    physics: "Modern Physics: Motion of charge carriers (electrons) in electric and magnetic fields.",
    chemistry: "Atomic Structure: Rules for electronic configuration: Hund’s rule of maximum multiplicity, stable configs.",
    zoology: "Excretory System: Nephron structure: Malpighian corpuscle, PCT, Loop of Henle, DCT, collecting duct.",
    botany: "Genetics: Deviations from Mendelian inheritance: Incomplete dominance and co-dominance.",
    mat: "Verbal Reasoning: Critical analytical reasoning passages."
  },
  {
    dayNumber: 24,
    phase: "High-Yield Core (Weeks 3-4)",
    physics: "Modern Physics: Millikan’s oil drop experiment (concept, calculations, charge quantization).",
    chemistry: "Classification of Elements: Modern periodic law and modern periodic table (s, p, d, and f block).",
    zoology: "Excretory System: Urine formation: Glomerular filtration, selective reabsorption, tubular secretion.",
    botany: "Genetics: Gene Linkage: Complete and incomplete linkage, Morgan's Drosophila experiments.",
    mat: "Spatial Relation: 3D net transformations (complex folded nets and dice)."
  },
  {
    dayNumber: 25,
    phase: "High-Yield Core (Weeks 3-4)",
    physics: "Modern Physics: Photoelectric effect: Wave theory failure, photon concept, work function.",
    chemistry: "Periodic Properties: Periodic trends of atomic size, ionic radius, and ionization potential.",
    zoology: "Excretory System: Counter-current multiplier mechanism for urine concentration, renal regulation.",
    botany: "Genetics: Crossing Over: Mechanism, chiasma formation, and significance in genetic variation.",
    mat: "Logical Sequencing: Sequential block diagrams."
  },
  {
    dayNumber: 26,
    phase: "High-Yield Core (Weeks 3-4)",
    physics: "Modern Physics: Photoelectric effect: Einstein’s photoelectric equation, stopping potential calculations.",
    chemistry: "Periodic Properties: Periodic trends of electron affinity, electronegativity, and metallic character.",
    zoology: "Excretory System: Abnormal constituents of urine, kidney stones, nephritis, dialyzer functionality.",
    botany: "Genetics: Sex-linked Inheritance: Haemophilia and color blindness inheritance patterns in humans.",
    mat: "Numerical Reasoning: Percentage calculation and profit-loss problems."
  },
  {
    dayNumber: 27,
    phase: "High-Yield Core (Weeks 3-4)",
    physics: "Modern Physics: Wave-particle duality: de-Broglie wave relation for light and particles.",
    chemistry: "Hydrocarbons: Alkanes: Nomenclature, isomerism, and general methods of preparation and properties.",
    zoology: "Circulatory System: Physiology of lymphatic system, composition of lymph, lymph nodes, spleen.",
    botany: "Genetics: Sex determination in humans, birds (ZW), and honeybees (haplo-diploid).",
    mat: "Spatial Relation: Abstract pattern matrices (3x3 grid)."
  },
  {
    dayNumber: 28,
    phase: "High-Yield Core (Weeks 3-4)",
    physics: "Dynamics: Kinetic energy of rolling objects (disc, cylinder, sphere) and conservation of energy.",
    chemistry: "Hydrocarbons: Alkenes: Structure, positional isomerism, Geometrical isomerism, and synthesis.",
    zoology: "Excretory System: Osmoregulation by Anti-diuretic hormone (ADH), Aldosterone, and RAAS system.",
    botany: "Genetics: Gene mutations (point, frameshift) and chromosomal abnormalities (numerical/structural).",
    mat: "Verbal Reasoning: Data sufficiency and syllogistic structures."
  },
  {
    dayNumber: 29,
    phase: "High-Yield Core (Weeks 3-4)",
    physics: "Rotational Dynamics: Torque-angular momentum relations, gyration radius formulations.",
    chemistry: "Hydrocarbons: Alkynes: Structure, acidic nature of terminal alkynes, addition reactions.",
    zoology: "Microbial Diseases: Bacteria: Structure of a bacterial cell, types of bacteria based on flagella.",
    botany: "Genetics: Genetic disorders: Clinical features of Down’s syndrome, Turner’s syndrome, Klinefelter's.",
    mat: "Logical Sequencing: Non-verbal classification (odd-one-out)."
  },
  {
    dayNumber: 30,
    phase: "High-Yield Core (Weeks 3-4)",
    physics: "Review: High-Yield Physics formulas and numericals (Dynamics, rotation, photo-electrics).",
    chemistry: "Review: Atomic models, quantum theory, periodic trends, and aliphatic hydrocarbons properties.",
    zoology: "Review: Human cardiac and renal systems physiology and associated diagnostic charts.",
    botany: "Review: Molecular genetics, replication, translation, Mendelian crosses, and genetic defects.",
    mat: "Review: Integrated High-Yield Mock Test (20 items)."
  },

  // ================= WEEKS 5-6 (DAYS 31 TO 45) — SECONDARY PILLARS =================
  {
    dayNumber: 31,
    phase: "Secondary Pillars (Weeks 5-6)",
    physics: "Fluid Statics: Hydrostatic pressure, upthrust, Archimedes' principle, and laws of floatation.",
    chemistry: "Chemical Bonding: Classical octet theory, ionic bond formation, lattice energy, Born-Haber cycle.",
    zoology: "Nervous System: Neuron structure, myelin sheath, resting membrane and Action potential generation.",
    botany: "Plant Physiology: Diffusion, osmosis, diffusion pressure deficit (DPD), and osmotic pressure.",
    mat: "Spatial Relation: Abstract reasoning - series completion cards."
  },
  {
    dayNumber: 32,
    phase: "Secondary Pillars (Weeks 5-6)",
    physics: "Fluid Statics: Surface tension: Cohesive and adhesive forces, surface energy, angle of contact.",
    chemistry: "Chemical Bonding: Covalent bond: Valency shell electronic pair repulsion (VSEPR) theory.",
    zoology: "Nervous System: Transmission of nerve impulses across synapses: Synaptic cleft, neurotransmitters.",
    botany: "Plant Physiology: Water potential concept, components: Solute potential, pressure potential.",
    mat: "Logical Sequencing: Symbol logic coding & rule detection."
  },
  {
    dayNumber: 33,
    phase: "Secondary Pillars (Weeks 5-6)",
    physics: "Fluid Statics: Capillary action, height of capillary rise, surface tension numerical calculation.",
    chemistry: "Chemical Bonding: Shapes of simple molecules (BeCl2, BF3, CH4, NH3, H2O) using VSEPR.",
    zoology: "Nervous System: Central Nervous System (CNS): Brain anatomy (forebrain, midbrain, hindbrain).",
    botany: "Plant Physiology: Plasmolysis, turgor pressure, wall pressure, and imbibition kinetics.",
    mat: "Numerical Reasoning: Simple interest & compound interest problems."
  },
  {
    dayNumber: 34,
    phase: "Secondary Pillars (Weeks 5-6)",
    physics: "Fluid Dynamics: Streamline and turbulent flow, coefficient of viscosity, Stokes' law, terminal velocity.",
    chemistry: "Chemical Bonding: Hybridization: concept, sp, sp2, sp3, sp3d, sp3d2 hybridizations with examples.",
    zoology: "Nervous System: Spinal cord anatomy, reflex arc, and reflex actions (conditioned vs unconditioned).",
    botany: "Plant Physiology: Water absorption mechanisms (active vs passive), ascent of sap, transpirational pull.",
    mat: "Spatial Relation: Shape analysis & rotation inside grid systems."
  },
  {
    dayNumber: 35,
    phase: "Secondary Pillars (Weeks 5-6)",
    physics: "Fluid Dynamics: Poiseuille's formula, Bernoulli's theorem (derivation, Venturi-meter, aerodynamic lift).",
    chemistry: "Chemical Bonding: Dipole moment, percentage ionic character in covalent bonds, Fajan's rules.",
    zoology: "Nervous System: Peripheral Nervous System (PNS) and Autonomic Nervous System (ANS: Sympathetic/Parasympathetic).",
    botany: "Plant Physiology: Transpiration process, types of transpiration, stomatal movement theories (active K+).",
    mat: "Verbal Reasoning: Logic puzzles & deductive reasoning grids."
  },
  {
    dayNumber: 36,
    phase: "Secondary Pillars (Weeks 5-6)",
    physics: "Wave Motion: General progressive wave equation, velocity of sound in solids, liquids, and gases.",
    chemistry: "Redox Reactions: Electronic concept of oxidation and reduction, oxidation number calculations.",
    zoology: "Sense Organs: Human Eye anatomy: Sclera, choroid, retina, lens, physiology of vision.",
    botany: "Plant Physiology: Guttation, wilting factors, transpiration factors.",
    mat: "Logical Sequencing: Advanced series logical continuity tests."
  },
  {
    dayNumber: 37,
    phase: "Secondary Pillars (Weeks 5-6)",
    physics: "Wave Motion: Effect of temperature, pressure, and humidity on the path velocity of sound in gases.",
    chemistry: "Redox Reactions: Balancing of redox reactions by oxidation number method (acidic/basic media).",
    zoology: "Sense Organs: Human Ear anatomy: External, middle, and inner ear (cochlea, organ of Corti).",
    botany: "Photosynthesis: Pigments (chlorophyll a, b, accessory pigments), Absorption and Action spectra.",
    mat: "Numerical Reasoning: Ratio and proportion calculations."
  },
  {
    dayNumber: 38,
    phase: "Secondary Pillars (Weeks 5-6)",
    physics: "Waves: Acoustics: Intensity level, loudness, decibel scales, quality, pitch of sound waves.",
    chemistry: "Redox Reactions: Balancing of redox reactions by ion-electron method, electrochemical applications.",
    zoology: "Nervous System: Physiology of hearing and balancing (vestibular apparatus).",
    botany: "Photosynthesis: Light reactions: Photolysis of water, cyclic & non-cyclic photophosphorylation (Z-scheme).",
    mat: "Spatial Relation: Spotting embedded figures & pattern completions."
  },
  {
    dayNumber: 39,
    phase: "Secondary Pillars (Weeks 5-6)",
    physics: "Optics: Reflection at curved mirrors, sign convention, mirror formula, spherical aberration.",
    chemistry: "Chemical Equilibrium: Physical and chemical equilibrium, law of mass action, Kp and Kc relations.",
    zoology: "Endocrinology: Glands: Pituitary, Thyroid, Parathyroid - hormones, functions, hyper/hypo disorders.",
    botany: "Photosynthesis: Dark reaction: Calvin-Benson cycle (C3 pathway) - steps, enzymes (RuBisCO).",
    mat: "Verbal Reasoning: Advanced statement-assumption argument tests."
  },
  {
    dayNumber: 40,
    phase: "Secondary Pillars (Weeks 5-6)",
    physics: "Optics: Refraction at plane surfaces, lateral shift, critical angle, total internal reflection.",
    chemistry: "Chemical Equilibrium: Le-Chatelier’s principle and its industrial applications (Haber's/Contact process).",
    zoology: "Endocrinology: Adrenal gland, Pancreas, Pineal, Thymus - hormones, blood sugar homeostasis.",
    botany: "Photosynthesis: Hatch-Slack pathway (C4 cycle) - Kranz anatomy, C3 vs C4 comparison.",
    mat: "Spatial Relation: Non-verbal classification grids."
  },
  {
    dayNumber: 41,
    phase: "Secondary Pillars (Weeks 5-6)",
    physics: "Optics: Refraction through lenses, lens maker’s formula, power of lens, chromatic aberration.",
    chemistry: "Ionic Equilibrium: Arrhenius theory of acids and bases, Bronsted-Lowry and Lewis concepts.",
    zoology: "Reproductive System: Male reproductive organs, histology of testes, hormonal regulations.",
    botany: "Photosynthesis: Photorespiration (C2 cycle) - organelles, factors affecting photosynthesis.",
    mat: "Logical Sequencing: Non-verbal grouping and matching."
  },
  {
    dayNumber: 42,
    phase: "Secondary Pillars (Weeks 5-6)",
    physics: "Optics: Young's double slit experiment (YDSE) - interference criteria, path difference, fringe width derivation.",
    chemistry: "Ionic Equilibrium: Ostwald's dilution law, ionic product of water, pH and pOH calculations.",
    zoology: "Reproductive System: Female reproductive organs, histology of ovaries, follicles.",
    botany: "Respiration: Aerobic respiration overview, glycolysis pathway (EMP pathway) - energy yield.",
    mat: "Numerical Reasoning: Statistics: Mean, median, mode calculations."
  },
  {
    dayNumber: 43,
    phase: "Secondary Pillars (Weeks 5-6)",
    physics: "Optics: Diffraction of light: Diffraction at single slit, diffraction grating, resolving power.",
    chemistry: "Ionic Equilibrium: Common ion effect, solubility product (Ksp) with related numericals.",
    zoology: "Reproductive System: Gametogenesis: Spermatogenesis details, sperm morphology.",
    botany: "Respiration: Oxidative decarboxylation of pyruvate, Krebs cycle (TCA cycle) - citric acid stages.",
    mat: "Spatial Relation: Paper cutting & folding pattern cards."
  },
  {
    dayNumber: 44,
    phase: "Secondary Pillars (Weeks 5-6)",
    physics: "Optics: Polarization of light: Brewster’s law, Malus's law, Polaroid sheets.",
    chemistry: "Ionic Equilibrium: Buffer solutions: Acidic and basic buffers, Henderson-Hasselbalch equation.",
    zoology: "Reproductive System: Gametogenesis: Oogenesis details, menstrual cycle, follicle-stimulating cycles.",
    botany: "Respiration: Electron Transport System (ETS), oxidative phosphorylation, anaerobic respiration.",
    mat: "Verbal Reasoning: Logic syllogisms & Venn interactions."
  },
  {
    dayNumber: 45,
    phase: "Secondary Pillars (Weeks 5-6)",
    physics: "Review: Wave optics equations, lenses, prisms, fluids formulas and Doppler mechanics.",
    chemistry: "Review: Bonding hybridization, redox balancing, chemical and ionic equilibrium constants.",
    zoology: "Review: Central nervous system, eyes, ears, endocrine feedback, and gametes development.",
    botany: "Review: Water transit, photosynthesis, glycolysis and cycle pathways diagrammatic points.",
    mat: "Review: Integrated Secondary Pillars progress evaluation test."
  },

  // ================= WEEKS 7-8 (DAYS 46 TO 60) — APPLIED & COMPLETE SWEEP =================
  {
    dayNumber: 46,
    phase: "Applied & Complete Sweep (Weeks 7-8)",
    physics: "Mechanics SHM: Simple harmonic motion criteria, displacement, velocity, energy curves.",
    chemistry: "Haloalkanes: Nomenclature, SN1 and SN2 reaction mechanisms (kinetics, stereochemistry).",
    zoology: "Selected Study: Plasmodium (Malaria parasite): habitat, morphology, structure.",
    botany: "Biodiversity: Bacteria: Reproduction, genetic recombination (conjugation, transduction).",
    mat: "Spatial Relation: Abstract pattern 3D matrices & net folds."
  },
  {
    dayNumber: 47,
    phase: "Applied & Complete Sweep (Weeks 7-8)",
    physics: "Mechanics: Simple pendulum, spring-mass system vibrations, energy conservation calculations.",
    chemistry: "Alcohols and Phenols: Nomenclature, industrial prep (oxo-process), fermentation, types (rectified).",
    zoology: "Selected Study: Plasmodium: Life cycle in mosquito and life cycle stages inside humans.",
    botany: "Biodiversity: Fungi and Lichens: General characters, classification, structure of Yeast, Mucor.",
    mat: "Logical Sequencing: Linear/Circular matrix relations."
  },
  {
    dayNumber: 48,
    phase: "Applied & Complete Sweep (Weeks 7-8)",
    physics: "Mechanics: Gravitational laws, variation of g with altitude/depth, escape velocity, satellites.",
    chemistry: "Alcohols and Phenols: Chemical properties of monohydric alcohols, acidic character of phenols.",
    zoology: "Selected Study: Earthworm (Pheretima): Habitat, external features, body wall structure.",
    botany: "Biodiversity: Algae: General features of Chlorophyceae, Rhodophyceae, Phaeophyceae, Spirogyra.",
    mat: "Numerical Reasoning: Simple probability and counting."
  },
  {
    dayNumber: 49,
    phase: "Applied & Complete Sweep (Weeks 7-8)",
    physics: "Mechanics: Elasticity: Hooke's law, stress-strain curve, Young's moduli, Poisson's ratio.",
    chemistry: "Ethers: Williamson’s synthesis of ethers, chemical properties of diethyl ether.",
    zoology: "Selected Study: Earthworm: Digestive system, circulatory system, and blood glands.",
    botany: "Biodiversity: Bryophytes: Characters of Liverworts, Hornworts, Moss. Structure of Marchantia.",
    mat: "Spatial Relation: Cross-sectional 3D shapes."
  },
  {
    dayNumber: 50,
    phase: "Applied & Complete Sweep (Weeks 7-8)",
    physics: "Heat: First law of thermodynamics, thermodynamic processes: isothermal, adiabatic, isobaric.",
    chemistry: "Aldehydes and Ketones: Nomenclature, isomerism, general prep from alcohols, acid chlorides.",
    zoology: "Selected Study: Frog (Rana): External features, skin anatomy, coloration.",
    botany: "Biodiversity: Pteridophytes: Characteristics, structure, reproduction of Dryopteris.",
    mat: "Verbal Reasoning: Logical passage inferences."
  },
  {
    dayNumber: 51,
    phase: "Applied & Complete Sweep (Weeks 7-8)",
    physics: "Heat: Entropy, heat engines, Carnot cycle, efficiency calculations, refrigerator coefficients.",
    chemistry: "Aldehydes and Ketones: Nucleophilic addition, aldol condensation, Cannizzaro's, benzaldehyde.",
    zoology: "Selected Study: Frog: Digestive system, respiration, circulatory system.",
    botany: "Biodiversity: Gymnosperms: Characteristics, structure, reproduction of Pinus.",
    mat: "Logical Sequencing: Analytical flowchart loops."
  },
  {
    dayNumber: 52,
    phase: "Applied & Complete Sweep (Weeks 7-8)",
    physics: "Electrostatics: Coulomb's law, electric field strength, field lines, potential energy.",
    chemistry: "Carboxylic Acids: Nomenclature, preparation methods, relative reactivity of derivatives.",
    zoology: "Selected Study: Frog: Excretory system, nervous, reproductive systems.",
    botany: "Morphology: Angiosperms: Root types, stem modifications, leaf anatomy.",
    mat: "Numerical Reasoning: Permutations and Combinations."
  },
  {
    dayNumber: 53,
    phase: "Applied & Complete Sweep (Weeks 7-8)",
    physics: "Electrostatics: Gauss's law, applications, capacitor principle, parallel plate capacitance.",
    chemistry: "Amines: Classification, isomerism, prep of primary amines, basicty comparison.",
    zoology: "Evolutionary Biology: Origin of life: Oparin-Haldane theory, Miller-Urey experiment.",
    botany: "Morphology: Angiosperms: Inflorescence, flower structure, floral formula, floral diagram.",
    mat: "Spatial Relation: Abstract rotation and matrix patterns."
  },
  {
    dayNumber: 54,
    phase: "Applied & Complete Sweep (Weeks 7-8)",
    physics: "Electricity: Ohm's law, resistance combinations, temperature coefficient, electrical energy.",
    chemistry: "Organometallic Compounds: General formulas and chemistry of Grignard reagents.",
    zoology: "Evolution: Evidences of evolution: Anatomical, embryological, paleontological.",
    botany: "Morphology: Angiosperms: Inflorescence, flower structure, floral formula, floral diagram.",
    mat: "Verbal Reasoning: Logic flowcharts & logical matrix."
  },
  {
    dayNumber: 55,
    phase: "Applied & Complete Sweep (Weeks 7-8)",
    physics: "Circuits: Kirchhoff's laws, wheatstone bridge, potentiometer as voltmeter, ammeter calculations.",
    chemistry: "Inorganic Chemistry: Non-metals: Ozone, preparation, nitric acid properties, chlorine oxides.",
    zoology: "Evolution: Theories of evolution: Lamarckism, Darwinism, and modern Neo-Darwinism.",
    botany: "Taxonomy: Diagnostic characters of Families Fabaceae and Liliaceae.",
    mat: "Logical Sequencing: Non-verbal completion series."
  },
  {
    dayNumber: 56,
    phase: "Applied & Complete Sweep (Weeks 7-8)",
    physics: "Alternating Currents: Peak vs RMS value, impedance, power calculations in L-R, C-R, L-R-C.",
    chemistry: "Inorganic Chemistry: Metallurgy principles (pyrometallurgy, concentration, calcination).",
    zoology: "Evolution: Human evolution: Ramapithecus to Homo Sapiens.",
    botany: "Embryology: Sporogenesis, gametogenesis in angiosperms, double fertilization.",
    mat: "Numerical Reasoning: Venn diagram counting & numerical sets."
  },
  {
    dayNumber: 57,
    phase: "Applied & Complete Sweep (Weeks 7-8)",
    physics: "Physics Devices: Semis: p-n junction, diode as rectifier, logic gates AND, OR, NOT, NAND, NOR.",
    chemistry: "Inorganic Chemistry: Extraction of Copper, Zinc, and Iron from their primary ores.",
    zoology: "Applied Zoology: Microbial diseases (Typhoid, TB, HIV, Cholera) pathology & prophylaxis.",
    botany: "Applied Botany: Concept and types of plant tissue culture and genetic engineering applications.",
    mat: "Spatial Relation: Form assembly & spatial folding tests."
  },
  {
    dayNumber: 58,
    phase: "Applied & Complete Sweep (Weeks 7-8)",
    physics: "Thermodynamics: Heat transmission: conduction, convection, radiation (Stefan Boltzmann).",
    chemistry: "Applied Chemistry: OSTWALD'S nitric acid prep, Haber's ammonia, Contact sulphuric acid process.",
    zoology: "Applied Zoology: Immunology: Innate and acquired immunity, vaccinations (toxoids, live).",
    botany: "Ecology: Ecosystem structure, carbon & nitrogen cycles, forest types of Nepal.",
    mat: "Verbal Reasoning: Matrix deduction & word sequence puzzles."
  },
  {
    dayNumber: 59,
    phase: "Applied & Complete Sweep (Weeks 7-8)",
    physics: "Wave particle: Bragg’s law of X-ray diffraction, radioactivity half-life and Curie units.",
    chemistry: "Analytic: Lassaigne’s test for N, S, P. Indicators selection in acid-base titrations.",
    zoology: "Medical Tech: Organ transplantation, IVF, amniocentesis, transgender models.",
    botany: "Ecology: Conservation biology: Biodiversity hotspots, Ramsar sites, Nepal reserves.",
    mat: "Logical Sequencing: Logical matrix completion & series logic."
  },
  {
    dayNumber: 60,
    phase: "Applied & Complete Sweep (Weeks 7-8)",
    physics: "Syllabus Synthesis: Mechanics, optics, nuclear decay, AC, logic gates final quick numerical formulas.",
    chemistry: "Syllabus Synthesis: Stoichiometry equations, transition element oxidation states, organic conversions.",
    zoology: "Syllabus Synthesis: Complete human system cycles, selected animals, and evolutionary benchmarks.",
    botany: "Syllabus Synthesis: Angiosperm floral formulas, embryology details, plant biochemistry revision.",
    mat: "Final Full MAT: Mock Mental Agility test with 20 distinct mixed reasoning items."
  }
];

if (typeof module !== 'undefined' && module.exports) {
  module.exports = studyDays;
}
