/* Générer particles.js en background */
particlesJS("particles-js", {
  particles: {
    number: {
      value: 100,
      density: {
        enable: true,
        value_area: 1042.21783956259
      }
    },
    color: {
      value: "#e8e8e8"
    },
    shape: {
      type: "circle",
      stroke: {
        width: 0,
        color: "#b400ff"
      },
      polygon: {
        nb_sides: 6
      },
      image: {
        src: "img/github.svg",
        width: 100,
        height: 100
      }
    },
    opacity: {
      value: 0.8498083922587271,
      random: true,
      anim: {
        enable: true,
        speed: 1.038961038961039,
        opacity_min: 0.1,
        sync: false
      }
    },
    size: {
      value: 1.5,
      random: true,
      anim: {
        enable: true,
        speed: 2.3976023976023977,
        size_min: 2.3976023976023977,
        sync: false
      }
    },
    line_linked: {
      enable: true,
      distance: 85,
      color: "#ffffff",
      opacity: 0.6012795228245711,
      width: 0.6413648243462091
    },
    move: {
      enable: true,
      speed: 6.413648243462092,
      direction: "none",
      random: false,
      straight: false,
      out_mode: "out",
      bounce: false,
      attract: {
        enable: false,
        rotateX: 1104.8066982851817,
        rotateY: 1200
      }
    }
  },
  interactivity: {
    detect_on: "canvas",
    events: {
      onhover: {
        enable: true,
        mode: "grab"
      },
      onclick: {
        enable: true,
        mode: "push"
      },
      resize: true
    },
    modes: {
      grab: {
        distance: 220,
        line_linked: {
          opacity: 0.5593853342327376
        }
      },
      bubble: {
        distance: 275.7242757242757,
        size: 11.988011988011989,
        duration: 1.2787212787212787,
        opacity: 0.0959040959040959,
        speed: 3
      },
      repulse: {
        distance: 135.86413586413587,
        duration: 0.4
      },
      push: {
        particles_nb: 4
      },
      remove: {
        particles_nb: 2
      }
    }
  },
  retina_detect: true
});

