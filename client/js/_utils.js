/**
 * Creates an HTML element with the specified content.
 * @param {String} html The html content.
 * @returns The HTMLElement
 */
const createHTMLElement = function(html) {
  const template = document.createElement('template');
  template.innerHTML = html;
  return template.content.firstElementChild;
}

/**
 * Creates an spinner / loading widget.
 * @param {String} text The optional / alternative text for the spinner.
 * @returns The HTMLElement
 */
const createLoadingHTMLOverlay = function(text) {
  return createHTMLElement(
    `<div class="spinner">
      ${ text || 'Loading&#8230;' }     
     </div>`
  );
};

export {
  createHTMLElement,
  createLoadingHTMLOverlay,
};